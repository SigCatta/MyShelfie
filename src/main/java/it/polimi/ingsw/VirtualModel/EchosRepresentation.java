package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.EchoMTC;

import java.util.Stack;

public class EchosRepresentation implements VirtualModelSubject {

    private final Stack<EchoMTC> messages;

    private static EchosRepresentation instance;

    private EchosRepresentation() {
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

    public EchoMTC getMessage(){
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
        synchronized (this){
            this.notify();
        }
    }

}