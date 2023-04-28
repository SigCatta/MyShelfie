package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;

import java.util.LinkedList;
import java.util.Queue;

public class ErrorsRepresentation implements VirtualModelSubject{

    private final Queue<String> ERROR_MESSAGES = new LinkedList<>();

    private static ErrorsRepresentation instance;

    private ErrorsRepresentation() {}

    public static ErrorsRepresentation getInstance() {
        if (instance == null) instance = new ErrorsRepresentation();
        return instance;
    }

    public void putError(ErrorMessageToClient errorMessage){
        ERROR_MESSAGES.add(errorMessage.getErrorMessage());
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
        //TODO every observer must be notified when the class changes
    }

}