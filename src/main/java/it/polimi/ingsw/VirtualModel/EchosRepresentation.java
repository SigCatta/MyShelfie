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

    /**
     * Adds a new message to the echo stack
     */
    public void putMessage(EchoMTC message) {
        messages.push(message);
        notifyObservers();
    }

    /**
     * Removes and returns the last received message from the stack
     */
    public EchoMTC popMessage() {
        if (messages.isEmpty()) return null;
        return messages.pop();
    }

    /**
     * Returns the last received message from the stack
     */
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
}