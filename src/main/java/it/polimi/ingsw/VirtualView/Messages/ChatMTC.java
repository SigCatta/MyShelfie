package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.VirtualModel.ChatRepresentation;

import java.io.Serializable;

public class ChatMTC implements MessageToClient, Serializable {

    private final String CHAT_MESSAGE;

    public ChatMTC(String chatMessage) {
        this.CHAT_MESSAGE = chatMessage;
    }

    public String getChatMessage() {
        return CHAT_MESSAGE;
    }

    @Override
    public void update() {
        ChatRepresentation.getInstance().addMessage(this);
    }
}
