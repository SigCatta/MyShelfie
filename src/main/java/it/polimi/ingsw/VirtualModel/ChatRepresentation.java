package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.ChatMTC;

import java.util.ArrayList;

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

    public ArrayList<ChatMTC> getMessages() {
        return CHAT;
    }

    public void addMessage(ChatMTC chatMessage) {
        CHAT.add(chatMessage);
        notifyObservers();
    }
}