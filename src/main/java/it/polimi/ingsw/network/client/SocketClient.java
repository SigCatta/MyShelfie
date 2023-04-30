package it.polimi.ingsw.network.client;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;
import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Client.Messages.HandshakeMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.View.VirtualView.Messages.MessageToClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static it.polimi.ingsw.InputReader.readLine;

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

    private SocketClient(String address, int port) throws Exception {
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);
        this.outputStm = new ObjectOutputStream(socket.getOutputStream());
        this.inputStm = new ObjectInputStream(socket.getInputStream());
        this.readExecutionQueue = Executors.newSingleThreadExecutor();
        Client.LOGGER.info("Connection established");
        clientInstance = this;
        // askToPlay(); //TODO uncomment after logic is fixed
    }

    public static synchronized Client getInstance() {
        if (clientInstance == null) return null; // can't create a socket without addres and port
        return clientInstance;
    }

    public static synchronized Client getInstance(String address, int port) throws Exception {
        if (clientInstance == null) clientInstance = new SocketClient(address, port);
        return clientInstance;
    }

    //TODO remove method when finished testing
    private void askToPlay() throws ExecutionException {
        String sel = readLine();
        if (sel.equals("j")) {
            System.out.println("what is the game id?");
            int id = Integer.parseInt(readLine());
            sendCommand(new CanIPlayMessage(id));
        } else {
            sendCommand(new NewGameMessage(2));
        }
    }

    /**
     * Asynchronously reads a message from the server via socket and notifies the InstructionDecoder
     */
    @Override
    public void readCommand() {
        readExecutionQueue.execute(() -> {

            while (!readExecutionQueue.isShutdown()) {
                try {
                    Object o = inputStm.readObject();
                    MessageToClient messageToClient = (MessageToClient) o;
                    ClientController.getInstance().visit(messageToClient);
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
     * @param message the message to be sent to the server
     */
    @Override
    public void sendCommand(MessageToServer message) {
        try {
            if (message instanceof HandshakeMessage) {
                this.nickname = message.getNickname();
            }
            outputStm.writeObject(message);
            outputStm.reset();
        } catch (IOException e) {
            Client.LOGGER.severe("An error occurred while sending the message");
            disconnect();
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