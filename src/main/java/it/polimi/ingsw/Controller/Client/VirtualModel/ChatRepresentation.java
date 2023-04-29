package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.ChatMessageToClient;

import java.util.LinkedList;
import java.util.Queue;

public class ChatRepresentation implements VirtualModelSubject{

    private final Queue<String> CHAT = new LinkedList<>();
    private static ChatRepresentation instance;

    private ChatRepresentation() {}

    public static ChatRepresentation getInstance() {
        if (instance == null) instance = new ChatRepresentation();
        return instance;
    }


    public void addMessage(ChatMessageToClient chatMessage){
        CHAT.add(chatMessage.getChatMessage());
        notifyObservers();
    }

    //TODO Note: if the client is not interested in the chat he can simply unsubscribe (remove from the observer list)
    //TODO and the messages will still be stored in the queue, so when he wants to read it again he can read all the
    //TODO messages he didn't read before

    @Override
    public void registerObserver(VirtualModelObserver observer) {

    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }
}