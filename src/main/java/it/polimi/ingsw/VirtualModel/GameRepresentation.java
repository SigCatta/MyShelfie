package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.Messages.GameMTC;

/**
 * Virtual model representation of the game
 */
public class GameRepresentation extends VirtualModelSubject {
    private GameMTC gameMessage;
    private static GameRepresentation instance;

    private GameRepresentation() {
        super();
    }

    public static GameRepresentation getInstance() {
        if (instance == null) instance = new GameRepresentation();
        return instance;
    }

    /**
     * Updates the game message
     */
    public void setGame(GameMTC gameMessage) {
        this.gameMessage = gameMessage;
        notifyObservers();
    }

    /**
     * Returns the maximum number of players
     */
    public int getMAX_PLAYER_NUMBER() {
        return gameMessage.getMAX_PLAYER_NUMBER();
    }

    /**
     * Returns the game ID
     */
    public int getGameID() {
        return gameMessage.getGameID();
    }

    /**
     * Returns the game state
     */
    public GameState getGameState() {
        if (gameMessage == null) return null;
        return gameMessage.getGameState();
    }

    /**
     * Returns the active player's name
     */
    public String getActivePlayerNickname() {
        return gameMessage.getActivePlayerNickname();
    }

    /**
     * Returns the game message
     */
    public GameMTC getGameMessage() {
        return gameMessage;
    }
}