package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;

import java.io.Serializable;

public class PingMessage implements Message, Serializable {
    @Override
    public void update() {
        Controller.getInstance().ping(this);
    }
}
