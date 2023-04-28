package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.ChatMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.View.VirtualView.Messages.ChatMessageToClient;
import it.polimi.ingsw.model.Game;

import java.util.HashMap;

public class ChatExecutor implements Executor {

    private Game game;

    public ChatExecutor(Game game){
        this.game = game;
    }

    @Override
    public void execute(MessageToServer data) {
        ChatMessage message = (ChatMessage) data;
        game.getVirtualView().send(new ChatMessageToClient(message.getChatMessage()));
    }
}
