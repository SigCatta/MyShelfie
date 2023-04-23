package it.polimi.ingsw.Controller.Server.PingPong;

import it.polimi.ingsw.Controller.Server.ServerMappable.PingMapper;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.TimerTask;

/**
 * sends a ping message to the client
 */
public class PingRoutine extends TimerTask implements Runnable{

    private final SocketClientHandler SOCKET_CLIENT_HANDLER;

    public PingRoutine(SocketClientHandler socketClientHandler){
        this.SOCKET_CLIENT_HANDLER = socketClientHandler;
    }

    @Override
    public void run() {
        // send PING message
        SOCKET_CLIENT_HANDLER.sendCommand(PingMapper.getMap());
        Server.LOGGER.info("PING sent");
    }
}
