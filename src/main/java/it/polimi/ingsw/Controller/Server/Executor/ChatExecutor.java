package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.ChatMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.View.VirtualView.Messages.ChatMessageToClient;
import it.polimi.ingsw.model.Game;

import java.util.HashMap;

public class ChatExecutor implements Executor {

    public static void execute(MessageToServer message) {
        Game game = message.getGame();
        ChatMessage chatMessage = (ChatMessage) message;
        game.getVirtualView().send(new ChatMessageToClient(chatMessage.getChatMessage()));
    }
}
