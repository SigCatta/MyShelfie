package it.polimi.ingsw.network.client;

import it.polimi.ingsw.Controller.PongController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient extends Client {

    private final Socket socket;

    private final ObjectOutputStream outputStm;
    private final ObjectInputStream inputStm;
    private final ExecutorService readExecutionQueue;

    private static final int SOCKET_TIMEOUT = 10000;

    public SocketClient(String address, int port, String nickname) throws IOException {
        this.nickname = nickname;
        //TODO call method to set Player's nicknames
        this.pongController = new PongController(this);
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);
        this.outputStm = new ObjectOutputStream(socket.getOutputStream());
        this.inputStm = new ObjectInputStream(socket.getInputStream());
        this.readExecutionQueue = Executors.newSingleThreadExecutor();
        Client.LOGGER.info("Connection established");
        client_instance = this;
        askToPlay();
    }

    public static synchronized Client getInstance() {
        return client_instance;
    }

    //TODO remove method when finished testing
    private void askToPlay() {
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("NICKNAME", getNickname());
        commandMap.put("GAME_ID", String.valueOf(1));
        commandMap.put("COMMAND_TYPE", "CAN_I_PLAY");
        sendCommand(commandMap);
    }

    /**
     * Asynchronously reads a message from the server via socket and notifies the InstructionDecoder
     */
    @Override
    public void readCommand() {
        readExecutionQueue.execute(() -> {

            while (!readExecutionQueue.isShutdown()) {
                HashMap<String, String> commandMap;
                try {
                    commandMap = (HashMap<String, String>) inputStm.readObject();
                    Client.LOGGER.info("Received: " + commandMap.get("COMMAND_TYPE"));

                    if(commandMap.get("COMMAND_TYPE").equals("PING")) {
                        pongController.onPingReceived();
                    }
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
        if (commandMap.get("COMMAND_TYPE").equals("CAN_I_PLAY") || commandMap.get("COMMAND_TYPE").equals("NEW_GAME")) {
            setGameId(Integer.parseInt(commandMap.get("GAME_ID")));
        }
        try {
            outputStm.writeObject(commandMap);
            Client.LOGGER.info("Command sent to the server with COMMAND_TYPE = " + commandMap.get("COMMAND_TYPE") +
                    " and NICKNAME = " + commandMap.get("NICKNAME") + " and GAME_ID = " + commandMap.get("GAME_ID"));
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