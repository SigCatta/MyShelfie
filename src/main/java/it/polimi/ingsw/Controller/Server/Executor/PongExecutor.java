package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.PongMTS;
import it.polimi.ingsw.network.server.SocketClientHandler;

/**
 * The PongExecutor class is responsible for executing the {@link PongMTS} command received from the client.
 * It notifies the ping controller that a pong message has been received.
 */
public class PongExecutor implements Executor {
    public static void execute(PongMTS message) {
        SocketClientHandler handler = message.getSocketClientHandler();
        handler.getPingController().onPongReceived();
    }
}
