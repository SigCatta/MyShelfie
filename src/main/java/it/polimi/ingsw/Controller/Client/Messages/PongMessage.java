package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;

import java.io.Serializable;

public class PongMessage extends MessageToServer implements Serializable {
    @Override
    public void update() {
        ServerController.getInstance().pongMessage(this);
    }
}
