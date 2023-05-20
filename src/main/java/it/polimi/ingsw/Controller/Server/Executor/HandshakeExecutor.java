package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.server.Server;

public class HandshakeExecutor implements Executor {
    /**
     * checks if the name is a valid one and adds it to the GamesManager set
     */
    public static void execute(HandshakeMTS message) {

        String nickname = message.getNewNickname();
        if (nickname == null) {
            message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.BADNICK, true));
            return;
        }
        if (nickname.length() == 0 || !GamesManager.getInstance().addNickname(nickname)) {
            message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.BADNICK, true));
            return;
        }
        Server.LOGGER.info("Handshake with server completed, hi " + nickname);
        message.getSocketClientHandler().setNickname(nickname);
        message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.NICKOK, false));
    }
}
