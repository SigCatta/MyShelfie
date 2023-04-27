package it.polimi.ingsw.network.client;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;
import it.polimi.ingsw.View.VirtualView.Messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Singleton class owned by each client,
 * handles the socket that communicates through the network
 */
public class SocketClient extends Client {
    private static Client clientInstance = null;
    private final Socket socket;
    private final ObjectOutputStream outputStm;
    private final ObjectInputStream inputStm;
    private final ExecutorService readExecutionQueue;

    private static final int SOCKET_TIMEOUT = 10000;

    public SocketClient(String address, int port, String nickname) throws IOException {
        this.nickname = nickname;
        //TODO call method to set Player's nicknames
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);
        this.outputStm = new ObjectOutputStream(socket.getOutputStream());
        this.inputStm = new ObjectInputStream(socket.getInputStream());
        this.readExecutionQueue = Executors.newSingleThreadExecutor();
        Client.LOGGER.info("Connection established");
        clientInstance = this;
        askToPlay();
    }

    public static synchronized Client getInstance() {
        return clientInstance;
    }

    //TODO remove method when finished testing
    private void askToPlay() {
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("NICKNAME", getNickname());
        commandMap.put("GAMEID", String.valueOf(1));
        commandMap.put("COMMAND", "NEW_GAME");
        commandMap.put("NUMBER_OF_PLAYERS", "2");
        sendCommand(commandMap);
    }

    /**
     * Asynchronously reads a message from the server via socket and notifies the InstructionDecoder
     */
    @Override
    public void readCommand() {
        readExecutionQueue.execute(() -> {

            while (!readExecutionQueue.isShutdown()) {
                try {
                    Message message = (Message) inputStm.readObject();
                    Controller.getInstance().updateVirtualModel(message);
                } catch (IOException | ClassNotFoundException e) {
                    //Connection lost with the server
                    Client.LOGGER.severe("An error occurred while reading the commandMap");
                    disconnect();
                    readExecutionQueue.shutdownNow();
                }
                //TODO: notify InstructionDecoder
            }
        });
    }

    /**
     * @param commandMap the commandMap to be sent to the server
     */
    @Override
    public void sendCommand(HashMap<String, String> commandMap) {
        if (commandMap.get("COMMAND").equals("CAN_I_PLAY") || commandMap.get("COMMAND").equals("NEW_GAME")) {
            setGameId(Integer.parseInt(commandMap.get("GAMEID"))); //TODO not possible with the new game since the gameid is unknown

            //TODO: new Timer(10)
        }
        try {
            outputStm.writeObject(commandMap);
            Client.LOGGER.info("Command sent to the server with COMMAND = " + commandMap.get("COMMAND") +
                    " and NICKNAME = " + commandMap.get("NICKNAME") + " and GAMEID = " + commandMap.get("GAMEID"));
            outputStm.reset();
        } catch (IOException e) {
            Client.LOGGER.severe("An error occurred while sending the commandMap");
            disconnect();
            //notifyObserver("Could not send commandMap.");
        }
    }

    /**
     * Disconnect the socket from the server.
     */
    @Override
    public void disconnect() {
        try {
            if (!socket.isClosed()) {
                readExecutionQueue.shutdownNow();
                socket.close();
                Client.LOGGER.severe("Client disconnected");
            }
            Client.LOGGER.severe("The socket wasn't opened when a disconnection was called");
        } catch (IOException e) {
            Client.LOGGER.severe("Could not disconnect.");
            //notifyObserver("Could not disconnect.");
        }

    }

}