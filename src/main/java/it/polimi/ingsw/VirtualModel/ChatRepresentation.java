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

    //TODO Note: if the client is not interested in the chat he can simply unsubscribe (remove from the observer list)
    //TODO and the messages will still be stored in the queue, so when he wants to read it again he can read all the
    //TODO messages he didn't read before

    @Override
    public void notifyObservers() {
        observers.forEach(VirtualModelObserver::update);
    }
}