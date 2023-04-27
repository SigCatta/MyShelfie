package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;

public class ErrorMessage implements Message{

    private final String ERROR_MESSAGE;

    public ErrorMessage(String errorMessage){
        this.ERROR_MESSAGE = errorMessage;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    @Override
    public void update() {
        Controller.getInstance().error(this);
    }
}
