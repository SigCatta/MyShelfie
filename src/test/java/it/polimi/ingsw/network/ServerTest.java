package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketClientHandler;
import it.polimi.ingsw.network.server.SocketServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class ServerTest {
    private static Server server;
    private static SocketServer socketServer;
    private static ClientHandler testClientHandler;
    private HashMap<String, String> commandMap;

    @BeforeEach
    void setUp() {
        server = new Server(5000);
        socketServer = new SocketServer(server, 5000);
        testClientHandler = new SocketClientHandler(socketServer, new Socket());
        testClientHandler.setGameId(1);
        testClientHandler.setNickname("testNickname");
        commandMap = new HashMap<>();
        commandMap.put("NICKNAME", "testNickname");
        commandMap.put("GAMEID", "1");
    }

    @Test
    public void addClientTest() {
        assertFalse(server.getClientHandlerMap().containsKey("testNickname"));
        server.addClient("testNickname", testClientHandler, commandMap);
        assertTrue(server.getClientHandlerMap().containsKey("1"));
        assertTrue(server.getClientHandlerMap().get("1").contains(testClientHandler));
        assertEquals(server.getClientHandler("testNickname", 1), testClientHandler);
    }

    @Test
    public void removeClientTest() {
        server.addClient("testNickname", testClientHandler, commandMap);
        assertTrue(server.getClientHandlerMap().containsKey("1"));
        assertTrue(server.getPingController().getClientMap().get("testNickname"));

        server.removeClient(testClientHandler);
        assertNull(server.getClientHandler("testNickname", 1));
        assertFalse(server.getPingController().getClientMap().get("testNickname"));
    }

    // TODO: Implement onCommandReceived tests when CommandParser is implemented




    @Test
    void getPingController() {
        assertNotNull(server.getPingController());
    }

    @Test
    void getInstanceTest() {
        assertNotNull(Server.getInstance());
    }

}