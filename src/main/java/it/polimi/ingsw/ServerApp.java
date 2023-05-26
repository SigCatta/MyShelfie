package it.polimi.ingsw;

import it.polimi.ingsw.network.server.ServerSocketAccepter;

/**
 * Main of the server app.
 */
public class ServerApp {

    public static void main(String[] args) {
        int serverPort = 28888; // default value

        ServerSocketAccepter serverSocketAccepter = new ServerSocketAccepter(serverPort);
        Thread thread = new Thread(serverSocketAccepter, "socketserver");
        thread.start();
    }
}