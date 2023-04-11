package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketClientHandler;
import it.polimi.ingsw.network.server.SocketServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;


public class ServerTest {
    private static Server server;
    private static SocketServer socketServer;
    private static ClientHandler testClientHandler;

    @BeforeEach
    void setUp() {
        server = new Server(5000);
        socketServer = new SocketServer(server, 5000);
        testClientHandler = new SocketClientHandler(socketServer, new Socket());
    }

    @Test
    public void addClientTest() {
        assertFalse(server.getClientHandlerMap().containsKey("testNickname"));
        server.addClient("testNickname", testClientHandler, null);
        assertTrue(server.getClientHandlerMap().containsKey("testNickname"));

        ClientHandler testClientHandler1 = new SocketClientHandler(socketServer, new Socket());
        server.addClient("testNickname", testClientHandler1, null);
        assertFalse(testClientHandler1.isConnected());
    }

    @Test
    public void removeClientTest() {
        server.addClient("testNickname", testClientHandler, null);
        assertTrue(server.getClientHandlerMap().containsKey("testNickname"));
        assertTrue(server.getPingController().getClientMap().get("testNickname"));

        server.removeClient("testNickname");
        assertFalse(server.getClientHandlerMap().containsKey("testNickname"));
        assertFalse(server.getPingController().getClientMap().get("testNickname"));
    }

    // TODO: Implement onCommandReceived tests when CommandParser is implemented


    @Test
    void getNicknameFromClientHandler() {
        server.addClient("test", testClientHandler, null);
        assertEquals("test", server.getNicknameFromClientHandler(testClientHandler));
    }

    @Test
    void getPingController() {
        assertNotNull(server.getPingController());
    }

    @Test
    void getInstanceTest() {
        assertNotNull(Server.getInstance());
    }

}