package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class GameMessageToClient implements MessageToClient, Serializable {

    private final int GAMEID;
    private String activePlayerNickname;

    public GameMessageToClient(Game game){
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
        ClientController.getInstance().changeGame(this);
    }
}
