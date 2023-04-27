package it.polimi.ingsw.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket server that handles all the new socket connection.
 */
public class ServerSocketAccepter implements Runnable {
    private final int port;
    ServerSocket serverSocket;

    /**
     * Constructor for the SocketServer class.
     *
     * @param port   the port on which the socket server should listen for connections
     */
    public ServerSocketAccepter(int port) {
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

                SocketClientHandler clientHandler = new SocketClientHandler(client);
                Thread thread = new Thread(clientHandler, "socketserver_handler" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                Server.LOGGER.severe("Connection dropped");
            }
        }
    }
}