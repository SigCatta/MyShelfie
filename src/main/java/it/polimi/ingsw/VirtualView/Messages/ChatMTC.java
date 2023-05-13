package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.VirtualModel.ChatRepresentation;

import java.io.Serializable;

public class ChatMTC implements MessageToClient, Serializable {

    private final String CHAT_MESSAGE;
    private final String SENDER;

    public ChatMTC(String chatMessage, String sender) {
        this.CHAT_MESSAGE = chatMessage;
        SENDER = sender;
    }

    public String getChatMessage() {
        return CHAT_MESSAGE;
    }

    public String getSender() {
        return SENDER;
    }

    @Override
    public void update() {
        ChatRepresentation.getInstance().addMessage(this);
    }
}
