package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class ChatMessage extends MessageToServer implements Serializable {

    private String chatMessage;

    public ChatMessage(String chatMessage){
        this.chatMessage = chatMessage;
    }

    @Override
    public void update() {
        ServerController.getInstance().chat(this);
    }

    public String getChatMessage() {
        return chatMessage;
    }
}
