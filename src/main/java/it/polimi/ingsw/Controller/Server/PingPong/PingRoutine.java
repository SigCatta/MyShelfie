package it.polimi.ingsw.Controller.Server.PingPong;

import it.polimi.ingsw.VirtualView.Messages.PingMTC;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.TimerTask;

/**
 * sends a ping message to the client
 */
public class PingRoutine extends TimerTask implements Runnable {

    private final SocketClientHandler SOCKET_CLIENT_HANDLER;
    private final PingController pingController;

    public PingRoutine(PingController pingController) {
        this.pingController = pingController;
        this.SOCKET_CLIENT_HANDLER = pingController.getSocketHandler();
    }

    @Override
    public void run() {
        // send PING message
        SOCKET_CLIENT_HANDLER.sendCommand(new PingMTC());
        //decrement the ping left to disconnect and if they are < 0 the connection was lost
        int pingLeft = pingController.decrementPingToDisconnect();
        if (pingLeft < 0) pingController.clientConnectionLost();
    }
}
