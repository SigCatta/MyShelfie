package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;

import java.io.Serializable;

public class ChatMessageToClient implements MessageToClient, Serializable {

    private final String CHAT_MESSAGE;

    public ChatMessageToClient(String chatMessage){
        this.CHAT_MESSAGE = chatMessage;
    }

    public String getChatMessage() {
        return CHAT_MESSAGE;
    }

    @Override
    public void update() {
        ClientController.getInstance().chat(this);
    }
}