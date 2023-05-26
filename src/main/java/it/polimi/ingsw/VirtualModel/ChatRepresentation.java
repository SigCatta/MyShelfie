package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.ChatMTC;

import java.util.ArrayList;

/**
 * Virtual model representation of the game chat
 */
public class ChatRepresentation extends VirtualModelSubject {
    private final ArrayList<ChatMTC> CHAT;
    private static ChatRepresentation instance;

    private ChatRepresentation() {
        super();
        CHAT = new ArrayList<>();
    }

    public static ChatRepresentation getInstance() {
        if (instance == null) instance = new ChatRepresentation();
        return instance;
    }

    /**
     * Returns the list of messages
     */
    public ArrayList<ChatMTC> getMessages() {
        return CHAT;
    }

    /**
     * Adds a new message to the list
     */
    public void addMessage(ChatMTC chatMessage) {
        CHAT.add(chatMessage);
        notifyObservers();
    }
}