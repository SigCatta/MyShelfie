package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.Enum.ErrorCode;
import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ErrorsRepresentation implements VirtualModelSubject {

    private final ArrayList<ErrorMessageToClient> ERRORS;

    private static ErrorsRepresentation instance;

    private ErrorsRepresentation() {
        ERRORS = new ArrayList<>();
    }

    public static ErrorsRepresentation getInstance() {
        if (instance == null) instance = new ErrorsRepresentation();
        return instance;
    }

    public ArrayList<ErrorCode> getErrorCodes() {
        return ERRORS.stream().map(ErrorMessageToClient::getErrorCode).collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeError(ErrorCode errorCode){
        ERRORS.removeIf(e -> e.getErrorCode() == errorCode);
    }

    public void putError(ErrorMessageToClient errorMessage) {
        ERRORS.add(errorMessage);
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