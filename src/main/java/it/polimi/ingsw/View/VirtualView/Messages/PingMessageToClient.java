package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;

import java.io.Serializable;

public class PingMessageToClient implements MessageToClient, Serializable {
    @Override
    public void update() {
        ClientController.getInstance().ping(this);
    }
}
