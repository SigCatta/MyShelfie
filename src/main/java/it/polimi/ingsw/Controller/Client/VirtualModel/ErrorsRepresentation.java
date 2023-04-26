package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.Controller.Server.VirtualView.Messages.ErrorMessage;

import java.util.LinkedList;
import java.util.Queue;

public class ErrorsRepresentation extends SingletonImplementation implements VirtualModelSubject{

    private final Queue<String> ERROR_MESSAGES = new LinkedList<>();

    private ErrorsRepresentation() {}

    public static ErrorsRepresentation getInstance() {
        return getInstance(ErrorsRepresentation.class);
    }

    public void putError(ErrorMessage errorMessage){
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