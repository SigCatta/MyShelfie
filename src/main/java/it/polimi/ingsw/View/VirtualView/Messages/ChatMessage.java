package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;

import java.io.Serializable;

public class ChatMessage implements Message, Serializable {

    private final String CHAT_MESSAGE;

    public ChatMessage(String chatMessage){
        this.CHAT_MESSAGE = chatMessage;
    }

    public String getChatMessage() {
        return CHAT_MESSAGE;
    }

    @Override
    public void update() {
        Controller.getInstance().chat(this);
    }
}
