package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

public class ChatMessage extends MessageToServer{

    private String chatMessage;

    public ChatMessage(String chatMessage){
        this.chatMessage = chatMessage;
    }

    @Override
    public void update(Game game) {
        ServerController.getInstance().chat(this, game);
    }

    public String getChatMessage() {
        return chatMessage;
    }
}
