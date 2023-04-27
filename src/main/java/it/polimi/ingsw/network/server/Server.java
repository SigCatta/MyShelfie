package it.polimi.ingsw.network.server;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Server singleton class that generates a socket server.
 */
public class Server {
    /**
     * Singleton instance
     */
    private static Server serverInstance;
    /**
     * Every nickname must be used by one and only one player
     */
    private final Set<String> nicknameSet;

    public static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private Server() {
        this.nicknameSet = Collections.synchronizedSet(new HashSet<>());
    }

    public static Server getInstance() {
        if(serverInstance == null) serverInstance = new Server();
        return serverInstance;
    }

}