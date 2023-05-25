package it.polimi.ingsw.network.server;

import java.util.logging.Logger;

/**
 * Server singleton class that generates a socket server.
 */
public class Server {
    /**
     * Singleton instance
     */
    private static Server serverInstance;

    public static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private Server() {
    }

    public static Server getInstance() {
        if (serverInstance == null) serverInstance = new Server();
        return serverInstance;
    }

}