package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class GameMessage implements Message, Serializable {

    private final int GAMEID;
    private final String ACTIVE_PLAYER_NICKNAME;

    public GameMessage(Game game){
        this.GAMEID = game.getGameID();
        this.ACTIVE_PLAYER_NICKNAME = game.getActivePlayer().getNickname();
    }

    public int getGameID() {
        return GAMEID;
    }

    public String getActivePlayerNickname() {
        return ACTIVE_PLAYER_NICKNAME;
    }

    @Override
    public void update() {
        Controller.getInstance().changeGame(this);
    }
}
