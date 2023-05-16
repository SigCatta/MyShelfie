package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.EchoMTC;

import java.util.Stack;

public class EchosRepresentation extends VirtualModelSubject {

    private Stack<EchoMTC> messages;

    private static EchosRepresentation instance;

    private EchosRepresentation() {
        super();
        messages = new Stack<>();
    }

    public static EchosRepresentation getInstance() {
        if (instance == null) instance = new EchosRepresentation();
        return instance;
    }

    public void putMessage(EchoMTC message) {
        messages.push(message);
        notifyObservers();
    }

    public EchoMTC popMessage() {
        if (messages.isEmpty()) return null;
        return messages.pop();
    }

    public EchoMTC peekMessage() {
        if (!messages.isEmpty()) {
            return messages.peek();
        }
        return null;
    }

    /**
     * removes all the messages from the stack
     */
    public void clean() {
        messages = new Stack<>();
    }

    //TODO method that retrieves the error message

    @Override
    public void notifyObservers() {
        observers.forEach(VirtualModelObserver::update);
        synchronized (this) {
            this.notify();
        }
        messages.pop();
    }
}