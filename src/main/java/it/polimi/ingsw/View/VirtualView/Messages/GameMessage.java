package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class GameMessage implements Message, Serializable {

    private final int GAMEID;
    private String activePlayerNickname;

    public GameMessage(Game game){
        this.GAMEID = game.getGameID();
        if(game.getActivePlayer() != null)
            this.activePlayerNickname = game.getActivePlayer().getNickname();
    }

    public int getGameID() {
        return GAMEID;
    }

    public String getActivePlayerNickname() {
        return activePlayerNickname;
    }

    @Override
    public void update() {
        Controller.getInstance().changeGame(this);
    }
}
