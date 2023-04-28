package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;

import java.io.Serializable;

public class ErrorMessageToClient implements MessageToClient, Serializable {

    private final String ERROR_MESSAGE;

    public ErrorMessageToClient(String errorMessage){
        this.ERROR_MESSAGE = errorMessage;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    @Override
    public void update() {
        ClientController.getInstance().error(this);
    }
}
