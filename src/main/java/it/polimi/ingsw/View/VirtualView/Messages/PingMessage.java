package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;

public class PingMessage implements Message{
    @Override
    public void update() {
        Controller.getInstance().ping(this);
    }
}
