package it.polimi.ingsw;

import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketServer;

/**
 * Main of the server app.
 */
public class ServerApp {

    public static void main(String[] args) {
        int serverPort = 28888; // default value
        int pingTimeout = 10000; //10 seconds

        //TODO: pass controller to server
        Server server = new Server(pingTimeout);
        server.getPingController().start();

        SocketServer socketServer = new SocketServer(server, serverPort);
        Thread thread = new Thread(socketServer, "socketserver");
        thread.start();
    }
}