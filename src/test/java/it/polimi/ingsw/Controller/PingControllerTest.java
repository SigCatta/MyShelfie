package it.polimi.ingsw.Controller;

import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketClientHandler;
import it.polimi.ingsw.network.server.SocketServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PingControllerTest {

    private PingController pingController;
    private Server server;
    private SocketServer socketServer;
    private SocketClientHandler testClientHandler;
    private HashMap<String, String> commandMap;

    @BeforeEach
    void setUp() {
        commandMap = new HashMap<>();
        commandMap.put("NICKNAME", "player1");
        commandMap.put("GAMEID", "1");
        server = new Server(1000);
        socketServer = new SocketServer(server, 5000);
        testClientHandler = new SocketClientHandler(socketServer, new Socket());
        testClientHandler.setGameId(1);
        testClientHandler.setNickname("player1");
        server.addClient("player1", testClientHandler, commandMap);
        pingController = server.getPingController();

    }

    @Test
    void addToPongMap() {
        pingController.addToClientMap("player1", 1);
        assertTrue(pingController.getClientMap().containsKey("player1"));
        assertTrue(pingController.getGameIdMap().containsKey("player1"));
        assertTrue(pingController.getClientMap().get("player1"));
        assertEquals(1, (int) pingController.getGameIdMap().get("player1"));
    }

    @Test
    void removeFromPongMap() {
        pingController.addToClientMap("player1", 1);
        pingController.removeFromClientMap("player1");
        assertFalse(pingController.getClientMap().containsKey("player1"));
        assertFalse(pingController.getGameIdMap().containsKey("player1"));
    }
}