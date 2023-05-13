package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.Controller.Client.MessageToServer;
import it.polimi.ingsw.VirtualView.Messages.ChatMTC;
import it.polimi.ingsw.model.Game;

public class ChatExecutor implements Executor {

    public static void execute(MessageToServer message) {
        Game game = message.getGame();
        ChatMTS chatMessage = (ChatMTS) message;
        game.getVirtualView().send(new ChatMTC(chatMessage.getChatMessage(), chatMessage.getSender()));
    }
}
