package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class CanIPlayMessage extends MessageToServer implements Serializable {

    public CanIPlayMessage(String nickname, int gameID){
        setGameId(gameID);
        setNickname(nickname);
    }

    @Override
    public void update() {
        ServerController.getInstance().canIPlay(this);
    }
}
