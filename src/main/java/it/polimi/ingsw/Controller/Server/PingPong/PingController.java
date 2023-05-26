package it.polimi.ingsw.Controller.Server.PingPong;

import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.Timer;

public class PingController extends Thread {
    private final Timer TIMER;
    private final int PING_TIMEOUT = 5000;
    private final int DELAY = 100;
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
        Server.LOGGER.info("PingController started");
        TIMER.scheduleAtFixedRate(new PingRoutine(this), DELAY, PING_TIMEOUT);
    }

    /**
     * Method called by the handler when it receives a PONG message from the client
     */
    public void onPongReceived() {
        pingToDisconnect = MAX_PING_FAILURES;
        if (connectionLost) {
            connectionLost = false;
            GamesManager.getInstance().onConnectionRestored(SOCKET_HANDLER);
        }
    }

    public int decrementPingToDisconnect() {
        return pingToDisconnect--;
    }

    public SocketClientHandler getSocketHandler() {
        return SOCKET_HANDLER;
    }

    /**
     * ping failed for too many times, that means the client was disconnected
     */
    public void clientConnectionLost() {
        connectionLost = true;
        GamesManager.getInstance().onConnectionLost(SOCKET_HANDLER);
    }

    public void close() {
        TIMER.cancel();
    }
}