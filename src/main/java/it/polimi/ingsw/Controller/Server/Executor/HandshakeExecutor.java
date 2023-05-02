package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.HandshakeMessage;
import it.polimi.ingsw.Controller.Server.ServerController.GamesManager;
import it.polimi.ingsw.Enum.ErrorCode;
import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;

public class HandshakeExecutor implements Executor{
    /**
     * checks if the name is a valid one and adds it to the GamesManager set
     */
    public static void execute(HandshakeMessage message){

        String nickname = message.getNewNickname();

        if(!GamesManager.getInstance().addNickname(nickname)){
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("nickname already taken", ErrorCode.BADNICK));
            System.out.println("nickname taken ");//TODO remove after testing
            return;
        }
        System.out.println("Handshake with server completed, hi " + nickname); //TODO remove
        message.getSocketClientHandler().setNickname(nickname);
    }
}
