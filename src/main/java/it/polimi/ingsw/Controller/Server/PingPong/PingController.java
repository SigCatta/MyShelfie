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
     * stops the execution
     */
    private boolean close;

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
        close = false;
    }

    /**
     * Starts the ping-pong communication.
     */
    @Override
    public void run() {
        Server.LOGGER.info("PingController started"); //TODO remove after testing

        while (!close){
            //if the client is disconnected the ping are sent less frequently
            int delay = DELAY;
            if(connectionLost) delay = 3*DELAY;

            TIMER.scheduleAtFixedRate(new PingRoutine(SOCKET_HANDLER), delay, PING_TIMEOUT);
            if(pingToDisconnect <= 0) clientConnectionLost();
            pingToDisconnect--;
        }
    }

    /**
     * Method called by the handler when it receives a PONG message from the client
     */
    public void onPongReceived() {
        pingToDisconnect = MAX_PING_FAILURES;
        connectionLost = false;

        GamesManager.getInstance().onConnectionRestored(SOCKET_HANDLER);
    }

    public void close(){
        close = true;
    }

    /**
     * ping failed MAX_PING_FAILURES times, that means the client was disconnected
     */
    private void clientConnectionLost(){
        connectionLost = true;

        GamesManager.getInstance().onConnectionLost(SOCKET_HANDLER);
    }

}