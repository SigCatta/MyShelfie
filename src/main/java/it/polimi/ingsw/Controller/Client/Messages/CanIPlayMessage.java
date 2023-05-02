package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;

import java.io.Serializable;

public class CanIPlayMessage extends MessageToServer implements Serializable {

    private int newGameID;

    public CanIPlayMessage(int newGameID){
        this.newGameID = newGameID;
    }

    @Override
    public void update() {
        ServerController.getInstance().canIPlay(this);
    }

    public int getNewGameID() {
        return newGameID;
    }
}
