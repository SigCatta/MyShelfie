package it.polimi.ingsw.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Socket server that handles all the new socket connection.
 */
public class SocketServer implements Runnable {
    private final Server server;
    private final int port;
    //TODO: add CommandParser
    ServerSocket serverSocket;

    /**
     * Constructor for the SocketServer class.
     *
     * @param server the server instance to which this socket server belongs
     * @param port   the port on which the socket server should listen for connections
     */
    public SocketServer(Server server, int port) {
        this.server = server;
        this.port = port;
    }

    /**
     * The main method of the SocketServer class that listens for client connections
     * and spawns a new SocketClientHandler for each connection.
     */
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            Server.LOGGER.info(() -> "Socket server started on port " + port + ".");
        } catch (IOException e) {
            Server.LOGGER.severe("Server could not start!");
            return;
        }

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket client = serverSocket.accept();

                SocketClientHandler clientHandler = new SocketClientHandler(this, client);
                Thread thread = new Thread(clientHandler, "socketserver_handler" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                Server.LOGGER.severe("Connection dropped");
            }
        }
    }

    /**
     * Handles the addition of a new client to the server.
     *
     * @param nickname      the nickname of the new client
     * @param clientHandler the ClientHandler representing the new client
     */
    public void addClient(String nickname, ClientHandler clientHandler, Map<String, String> commandMap) {
        server.addClient(nickname, clientHandler, commandMap);
    }

    /**
     * Forwards a received commandMap from a client to the main Server class.
     *
     * @param commandMap the commandMap to be forwarded
     */
    public void onCommandReceived(HashMap<String, String> commandMap) {
        server.onCommandReceived(commandMap);
    }

    /**
     * Handles the disconnection of a client from the server.
     *
     * @param clientHandler the ClientHandler representing the disconnecting client
     */
    public void onDisconnect(ClientHandler clientHandler) {
        server.onDisconnect(clientHandler);
    }
}