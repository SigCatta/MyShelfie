package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class CanIPlayMessage extends MessageToServer implements Serializable {

    private String newNickname;
    private int newGameID;

    public CanIPlayMessage(String newNickname, int newGameID){
        this.newNickname = newNickname;
        this.newGameID = newGameID;
    }

    @Override
    public void update() {
        ServerController.getInstance().canIPlay(this);
    }

    public String getNewNickname() {
        return newNickname;
    }

    public int getNewGameID() {
        return newGameID;
    }
}
