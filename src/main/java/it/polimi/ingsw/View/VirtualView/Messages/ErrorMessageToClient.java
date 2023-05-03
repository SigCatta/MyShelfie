package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;
import it.polimi.ingsw.Enum.ErrorCode;

import java.io.Serializable;

public class ErrorMessageToClient implements MessageToClient, Serializable {

    private final String ERROR_MESSAGE;
    private final ErrorCode ERROR_CODE;

    public ErrorMessageToClient(String errorMessage, ErrorCode errorCode) {
        this.ERROR_MESSAGE = errorMessage;
        this.ERROR_CODE = errorCode;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    public ErrorCode getErrorCode() {
        return ERROR_CODE;
    }

    @Override
    public void update() {
        ClientController.getInstance().error(this);
    }
}
