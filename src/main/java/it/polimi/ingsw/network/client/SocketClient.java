package it.polimi.ingsw.network.client;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.Controller.Client.MessageToServer;
import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.VirtualView.Messages.MessageToClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Singleton class owned by each client,
 * handles the socket that communicates through the network
 */
public class SocketClient extends Client {
    private static Client clientInstance;
    private Socket socket;
    private ObjectOutputStream outputStm;
    private ObjectInputStream inputStm;
    private ExecutorService readExecutionQueue;
    private String nickname;
    private static final int SOCKET_TIMEOUT = 10000000;

    private SocketClient(String address, int port) {

        this.socket = new Socket();
        try {
            this.socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);
            this.outputStm = new ObjectOutputStream(socket.getOutputStream());
            this.inputStm = new ObjectInputStream(socket.getInputStream());
            this.readExecutionQueue = Executors.newSingleThreadExecutor();
            Client.LOGGER.info("Connection established");
            clientInstance = this;
            new Thread(InputStatePlayer.getInstance()).start(); // from now on the user can execute commands
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Client getInstance() {
        if (clientInstance == null) clientInstance = new SocketClient("localhost", 28888); // can't create a socket without addres and port -- should never happen!!
        return clientInstance;
    }

    public static synchronized Client getInstance(String address, int port) {
        if (clientInstance == null) clientInstance = new SocketClient(address, port);
        return clientInstance;
    }

    /**
     * Asynchronously reads a message from the server via socket and notifies the InstructionDecoder
     */
    @Override
    public synchronized void readCommand() {
        readExecutionQueue.execute(() -> {

            while (!readExecutionQueue.isShutdown()) {
                try {
                    Object o = inputStm.readObject();
                    MessageToClient messageToClient = (MessageToClient) o;
                    messageToClient.update();
                } catch (IOException | ClassNotFoundException e) {
                    //Connection lost with the server
                    Client.LOGGER.severe("Did you remember to implement Serilizable?");
                    disconnect();
                    readExecutionQueue.shutdownNow();
                }
                //TODO: notify InstructionDecoder
            }
        });
    }

    /**
     * @param message the message to be sent to the server
     */
    @Override
    public synchronized void sendCommand(MessageToServer message) {
        try {
            if (message instanceof HandshakeMTS) {
                this.nickname = message.getNickname();
                super.setNickname(this.nickname);
            }
            outputStm.writeObject(message);
            outputStm.reset();
        } catch (IOException e) {
            Client.LOGGER.severe("An error occurred while sending the message");
        }
    }

    /**
     * Disconnect the socket from the server.
     */
    @Override
    public void disconnect() {
        try {
            if (!socket.isClosed()) {
                if (readExecutionQueue != null)
                    readExecutionQueue.shutdownNow();
                socket.close();
                Client.LOGGER.severe("Client disconnected");
            }
            Client.LOGGER.severe("The socket wasn't opened when a disconnection was called");
        } catch (IOException e) {
            Client.LOGGER.severe("Could not disconnect.");
        }

    }

}