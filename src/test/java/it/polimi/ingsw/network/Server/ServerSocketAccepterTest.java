package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.ServerSocketAccepter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertTrue;

public class ServerSocketAccepterTest {
    private static final int TEST_PORT = 9999;
    private Server server;
    private Thread serverThread;

    @BeforeEach
    public void setUp() {
        server = Server.getInstance();
        serverThread = new Thread(new ServerSocketAccepter(TEST_PORT));
        serverThread.start();
        try {
            Thread.sleep(1000); // Time for server to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        serverThread.interrupt();
    }


    @Test
    public void testConnectingToServer() {
        try {
            Socket client = new Socket("localhost", TEST_PORT);
            assertTrue(client.isConnected());
            client.close();
        } catch (IOException e) {
            System.out.println("Client could not connect to server");
        }
    }

    //TODO: Add more tests for various scenarios.
}