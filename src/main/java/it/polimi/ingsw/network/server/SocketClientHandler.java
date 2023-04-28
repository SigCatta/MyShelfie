package it.polimi.ingsw.network.server;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Server.ServerController.GamesManager;
import it.polimi.ingsw.Controller.Server.PingPong.PingController;
import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;
import it.polimi.ingsw.View.VirtualView.Messages.MessageToClient;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * One instance for each client in a Thread, useful to direct traffic
 */
public class SocketClientHandler extends ClientHandler implements Runnable {
    private final Socket client;
    private PingController pingController;
    private boolean connected;

    /**
     * Each handler is assigned to only one client,
     * the nickname is a primary key for a player
     */
    private String nickname;
    private int gameID;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    /**
     * @param client the client asking to connect
     */
    public SocketClientHandler(Socket client) {
        this.client = client;
        pingController = new PingController(this);

        try {
            this.output = new ObjectOutputStream(client.getOutputStream());
            this.input = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            Server.LOGGER.severe(e.getMessage());
        }
    }
    @Override
    public void run() {
        try {
            pingController.start();
            handleClientMessages();
        }catch(InvalidClassException inc){
            sendCommand(new ErrorMessageToClient("Provide a valid command to start or join a game"));
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            Server.LOGGER.severe("Client " + client.getInetAddress() + " connection dropped.");//TODO remove after testing
            Thread.currentThread().interrupt();
        } catch (ClassNotFoundException classNotFoundException){
            Server.LOGGER.severe("Client " + client.getInetAddress() + " class not found"); //TODO remove after testing
            Thread.currentThread().interrupt();
        } catch (NumberFormatException nfe){
            Server.LOGGER.severe("Client " + client.getInetAddress() + " invalid number"); //TODO remove after testing
            Thread.currentThread().interrupt();
        }
    }

    /**
     * gets the messages from the input and forwards them to the GamesManager that handles the traffic
     */
    private void handleClientMessages() throws IOException, ClassNotFoundException {
        Server.LOGGER.info("Client connected from " + client.getInetAddress()); //TODO remove after testing

        while (!Thread.currentThread().isInterrupted()) {
            Object o = input.readObject();
            MessageToServer message = (MessageToServer) o;

            //set the header necessary to identify the player in a game
            message.setSocketClientHandler(this);
            if(nickname != null){
                message.setGameId(this.gameID);
                message.setNickname(this.nickname);
            }

            GamesManager.getInstance().onCommandReceived(message);
        }

        pingController.close();
        client.close();
    }

    /**
     * Disconnect the socket.
     */
    @Override
    public void disconnect() {
        if (!connected) return;

        try {
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            Server.LOGGER.severe(e.getMessage());
        }
        connected = false;

        GamesManager.getInstance().removePlayer(this);

        Thread.currentThread().interrupt();
    }

    /**
     * Sends a command to the client via socket.
     *
     * @param commandMap the command and overhead data to be sent.
     */
    @Override
    public void sendCommand(HashMap<String, String> commandMap) {
        try {
            output.writeObject(commandMap);
            output.reset();
            Server.LOGGER.info("Command sent to the client " + nickname + "with COMMAND = " + commandMap.get("COMMAND") +
                    " and NICKNAME = " + commandMap.get("NICKNAME") + " and GAME_ID = " + commandMap.get("GAMEID"));
        } catch (IOException e) {
            Server.LOGGER.severe(e.getMessage());
            disconnect();
        }
    }
    @Override
    public void sendCommand(MessageToClient messageToClient){
        try {
            output.writeObject(messageToClient);
            output.reset();
        } catch (IOException e) {
            Server.LOGGER.severe(e.getMessage());
            disconnect();
        }
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getGameID() {
        return gameID;
    }
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

}