package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.EchoToClient;

import java.util.Stack;

public class EchosRepresentation implements VirtualModelSubject {

    private final Stack<EchoToClient> messages;

    private static EchosRepresentation instance;

    private EchosRepresentation() {
        messages = new Stack<>();
    }

    public static EchosRepresentation getInstance() {
        if (instance == null) instance = new EchosRepresentation();
        return instance;
    }

    public void putMessage(EchoToClient message) {
        messages.push(message);
        notifyObservers();
    }

    public EchoToClient getMessage(){
        if (!messages.isEmpty()) return messages.pop();
        return null;
    }

    //TODO method that retrieves the error message

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