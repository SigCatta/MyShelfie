package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.PongMTS;
import it.polimi.ingsw.network.server.SocketClientHandler;

public class PongExecutor implements Executor{
    public static void execute(PongMTS message){
        SocketClientHandler handler = message.getSocketClientHandler();
        handler.getPingController().onPongReceived();
    }
}
