package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Controller.Server.PingPong.PingController;
import it.polimi.ingsw.network.server.SocketClientHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;

class PingClientControllerTest {

    private Thread client;
    private SocketClientHandler socketClientHandler;
    private PingController pingController;

    @BeforeEach
    public void setUp() {
        socketClientHandler = new SocketClientHandler(new Socket());
        pingController = new PingController(socketClientHandler);
    }
    @Test
    public void isPingSent(){
    }

}