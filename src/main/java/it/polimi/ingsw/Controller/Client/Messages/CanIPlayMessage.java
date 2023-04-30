package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class CanIPlayMessage extends MessageToServer implements Serializable {
    private String nickname;

    private int gameID;

    public CanIPlayMessage(String nickname, int gameID){
        this.nickname = nickname;
        this.gameID = gameID;
    }

    @Override
    public void update() {
        ServerController.getInstance().canIPlay(this);
    }
}
