package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.GeneralMessageToClient;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GeneralMessagesRepresentation implements VirtualModelSubject{
    private final ArrayList<GeneralMessageToClient> messages;
    private static GeneralMessagesRepresentation instance;

    private GeneralMessagesRepresentation() {
        this.messages = new ArrayList<>();
    }

    public static GeneralMessagesRepresentation getInstance(){
        if (instance == null) instance = new GeneralMessagesRepresentation();
        return instance;
    }


    public ArrayList<String> getCodes(){
        return messages.stream().map(GeneralMessageToClient::getCode).collect(Collectors.toCollection(ArrayList::new));
    }

    public void putMessage(GeneralMessageToClient message){
        messages.add(message);
    }
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
