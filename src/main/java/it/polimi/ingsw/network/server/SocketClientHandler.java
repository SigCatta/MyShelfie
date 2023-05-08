package it.polimi.ingsw.network.server;

import it.polimi.ingsw.Controller.Client.MessageToServer;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.Controller.Server.PingPong.PingController;
import it.polimi.ingsw.VirtualView.Messages.MessageToClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * One instance for each client in a Thread, useful to direct traffic
 */
public class SocketClientHandler extends ClientHandler implements Runnable {
    private final Socket client;

    private PingController pingController;

    /**
     * Each handler is assigned to only one client,
     * the nickname is a primary key for a player
     */
    private String nickname;
    private int gameID;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private boolean stop;
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
        }catch (IOException e) {
            Server.LOGGER.severe("Client " + client.getInetAddress() + " connection dropped. (socketClientHandler)");//TODO remove after testing
            disconnect();
        } catch (ClassNotFoundException classNotFoundException){
            Server.LOGGER.severe("Client " + client.getInetAddress() + " class not found"); //TODO remove after testing
            disconnect();
        } catch (NumberFormatException nfe){
            Server.LOGGER.severe("Client " + client.getInetAddress() + " invalid number"); //TODO remove after testing
            disconnect();
        }
    }
    /**
     * gets the messages from the input and forwards them to the GamesManager that handles the traffic
     */
    private void handleClientMessages() throws IOException, ClassNotFoundException {
        Server.LOGGER.info("Client connected from " + client.getInetAddress()); //TODO remove after testing

        while (!stop) {
            Object o = input.readObject();
            MessageToServer message = (MessageToServer) o;

            //set the header necessary to identify the player in a game
            message.setSocketClientHandler(this);
            message.setGameId(this.gameID);
            message.setNickname(this.nickname);

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

        try {
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            Server.LOGGER.severe(e.getMessage());
        }

        GamesManager.getInstance().removePlayer(this);
        pingController.close();

        stop = true;
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
    public PingController getPingController() {
        return pingController;
    }
    public int getGameID() {
        return gameID;
    }
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}