package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;

/**
 * This class represents a message to the client containing information about the game state.
 */
public class GameMTC implements MessageToClient, Serializable {

    private final int GAMEID;
    private final int MAX_PLAYER_NUMBER;
    private String activePlayerNickname;

    private final GameState GAME_STATE;
    private final int numOfPlayers;

    public GameMTC(Game game) {
        this.GAMEID = game.getGameID();
        this.MAX_PLAYER_NUMBER = game.getMAX_PLAYER_NUMBER();
        GAME_STATE = game.getGameState();
        numOfPlayers = game.getMAX_PLAYER_NUMBER();
        if (game.getActivePlayer() != null)
            this.activePlayerNickname = game.getActivePlayer().getNickname();
    }

    public int getGameID() {
        return GAMEID;
    }

    public int getMAX_PLAYER_NUMBER() {
        return MAX_PLAYER_NUMBER;
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
