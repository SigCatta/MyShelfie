package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class GameStateObserver implements VirtualModelObserver {

    public GameState previousState;

    public GameStateObserver() {
        previousState = GameRepresentation.getInstance().getGameState();
        GameRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        if (previousState == GameRepresentation.getInstance().getGameState()) return;

        previousState = GameRepresentation.getInstance().getGameState();

        if (BoardController.getInstance() != null) {
            BoardController.getInstance().updateGameState();
        }
    }
}
