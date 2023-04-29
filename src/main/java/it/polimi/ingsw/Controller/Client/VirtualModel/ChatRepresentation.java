package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.ChatMessage;

import java.util.LinkedList;
import java.util.Queue;

public class ChatRepresentation extends SingletonImplementation implements VirtualModelSubject{

    private final Queue<String> CHAT = new LinkedList<>();

    private ChatRepresentation() {}

    public static ChatRepresentation getInstance() {
        return getInstance(ChatRepresentation.class);
    }

    public void addMessage(ChatMessage chatMessage){
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