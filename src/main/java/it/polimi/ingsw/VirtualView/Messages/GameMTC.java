package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class GameMTC implements MessageToClient, Serializable {

    private final int GAMEID;
    private String activePlayerNickname;

    private final GameState GAME_STATE;
    private final int numOfPlayers;

    public GameMTC(Game game) {
        this.GAMEID = game.getGameID();
        GAME_STATE = game.getGameState();
        numOfPlayers = game.getMAX_PLAYER_NUMBER();
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

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    @Override
    public void update() {
        GameRepresentation.getInstance().setGame(this);
    }
}
