package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;

public class HandshakeExecutor implements Executor{
    /**
     * checks if the name is a valid one and adds it to the GamesManager set
     */
    public static void execute(HandshakeMTS message){

        String nickname = message.getNewNickname();
        if(nickname == null) {
            message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.BADNICK, true));
            return;
        }
        if (nickname.length() == 0 || !GamesManager.getInstance().addNickname(nickname)) {
            message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.BADNICK, true));
            System.out.println("nickname taken ");//TODO remove after testing
            return;
        }
        System.out.println("Handshake with server completed, hi " + nickname); //TODO remove
        message.getSocketClientHandler().setNickname(nickname);
        message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.NICKOK, false));
    }
}
