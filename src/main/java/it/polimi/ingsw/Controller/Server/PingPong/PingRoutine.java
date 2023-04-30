package it.polimi.ingsw.Controller.Server.PingPong;

import it.polimi.ingsw.View.VirtualView.Messages.PingMessageToClient;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.TimerTask;

/**
 * sends a ping message to the client
 */
public class PingRoutine extends TimerTask implements Runnable{

    private final SocketClientHandler SOCKET_CLIENT_HANDLER;
    private PingController pingController;

    public PingRoutine(PingController pingController){
        this.pingController = pingController;
        this.SOCKET_CLIENT_HANDLER = pingController.getSocketHandler();
    }

    @Override
    public void run() {
        // send PING message
        SOCKET_CLIENT_HANDLER.sendCommand(new PingMessageToClient());
        //decrement the ping left to disconnect and if they are < 0 the connection was lost
        int pingLeft = pingController.decrementPingToDisconnect();
        if(pingLeft < 0) pingController.clientConnectionLost();

        Server.LOGGER.info("PING sent : " + pingLeft + " ping left to disconnect");//TODO remove
    }
}
