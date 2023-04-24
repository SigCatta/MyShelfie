package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketClientHandler;
import it.polimi.ingsw.network.server.ServerSocketAccepter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.net.Socket;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class ServerTest {
    private static Server server;
    private static ServerSocketAccepter serverSocketAccepter;
    private static ClientHandler testClientHandler;
    private HashMap<String, String> commandMap;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void addClientTest() {

    }

    @Test
    public void removeClientTest() {

    }

    // TODO: Implement onCommandReceived tests when CommandParser is implemented

    @Test
    void getPingController() {}

    @Test
    void getInstanceTest() {
        assertNotNull(Server.getInstance());
    }

}