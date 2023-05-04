package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.VirtualModel.GameRepresentation;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.GameState;

import java.io.Serializable;

public class GameMessageToClient implements MessageToClient, Serializable {

    private final int GAMEID;
    private String activePlayerNickname;

    private final GameState GAME_STATE;

    public GameMessageToClient(Game game) {
        this.GAMEID = game.getGameID();
        GAME_STATE = game.getGameState();
        if (game.getActivePlayer() != null)
            this.activePlayerNickname = game.getActivePlayer().getNickname();
    }

    public int getGameID() {
        return GAMEID;
    }

    public GameState getGameState() {
        return GAME_STATE;
    }

    public String getActivePlayerNickname() {
        return activePlayerNickname;
    }

    @Override
    public void update() {
        GameRepresentation.getInstance().setGame(this);
    }
}
