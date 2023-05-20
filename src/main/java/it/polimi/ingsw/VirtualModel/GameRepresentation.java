package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.Messages.GameMTC;

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

    public void setGame(GameMTC gameMessage) {
        this.gameMessage = gameMessage;
        notifyObservers();
    }

    public int getMAX_PLAYER_NUMBER() {
        return gameMessage.getMAX_PLAYER_NUMBER();
    }

    public int getGameID() {
        return gameMessage.getGameID();
    }

    public GameState getGameState() {
        if (gameMessage == null) return null;
        return gameMessage.getGameState();
    }

    public String getActivePlayerNickname() {
        return gameMessage.getActivePlayerNickname();
    }

    public GameMTC getGameMessage() {
        return gameMessage;
    }
}