package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.VirtualModel.ChatRepresentation;

import java.io.Serializable;

/**
 * This class represents a message to the client containing a chat message.
 */
public class ChatMTC implements MessageToClient, Serializable {

    private final String CHAT_MESSAGE;
    private final String SENDER;

    private final String RECEIVER;
    private final boolean isBroadcast;

    public ChatMTC(String chatMessage, String sender, String receiver, boolean isBroadcast) {
        this.CHAT_MESSAGE = chatMessage;
        this.SENDER = sender;
        this.RECEIVER = receiver;
        this.isBroadcast = isBroadcast;
    }

    public String getChatMessage() {
        return CHAT_MESSAGE;
    }

    public String getSender() {
        return SENDER;
    }

    public String getRECEIVER() {
        return RECEIVER;
    }

    public boolean isBroadcast() {
        return isBroadcast;
    }

    @Override
    public void update() {
        ChatRepresentation.getInstance().addMessage(this);
    }
}
