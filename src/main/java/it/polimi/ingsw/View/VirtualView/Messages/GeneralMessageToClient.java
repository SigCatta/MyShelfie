package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;

import java.io.Serializable;

public class GeneralMessageToClient implements MessageToClient, Serializable {
    private final String message;
    private final String code;

    public GeneralMessageToClient(String message, String messageID) {
        this.message = message;
        this.code = messageID;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public void update() {
        ClientController.getInstance().general(this);
    }
}
