package it.polimi.ingsw.Controller.Server.PingPong;

import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.*;

public class PingController extends Thread{
    private final Timer TIMER;
    private final int PING_TIMEOUT = 500;
    private final int DELAY = 500;
    private boolean connectionLost;

    /**
     * if the client does not respond to MAX_PING_FAILURES consecutive
     * ping, then he will be disconnected
     */
    private final int MAX_PING_FAILURES = 4;
    private int pingToDisconnect;
    private final SocketClientHandler SOCKET_HANDLER;

    public PingController(SocketClientHandler socketClientHandler) {
        this.TIMER = new Timer();
        this.SOCKET_HANDLER = socketClientHandler;
        pingToDisconnect = MAX_PING_FAILURES;
        connectionLost = false;
    }

    /**
     * Starts the ping-pong communication.
     */
    @Override
    public void run() {
        Server.LOGGER.info("PingController started"); //TODO remove after testing

        //if the client is disconnected the ping are sent less frequently
        int pingTimeout = PING_TIMEOUT;
        if(connectionLost) pingTimeout = 3*PING_TIMEOUT;

        TIMER.scheduleAtFixedRate(new PingRoutine(SOCKET_HANDLER), DELAY, pingTimeout);
        if(pingToDisconnect <= 0) clientConnectionLost();
        pingToDisconnect--;
    }

    /**
     * Method called by the handler when it receives a PONG message from the client
     */
    public void onPongReceived() {
        pingToDisconnect = MAX_PING_FAILURES;
        if(!connectionLost) return;

        connectionLost = false;
        GamesManager.getInstance().onConnectionRestored(SOCKET_HANDLER);
    }

    public void close(){
        TIMER.cancel();
    }

    /**
     * ping failed MAX_PING_FAILURES times, that means the client was disconnected
     */
    private void clientConnectionLost(){
        if(connectionLost) return;

        connectionLost = true;
        GamesManager.getInstance().onConnectionLost(SOCKET_HANDLER);
    }

}