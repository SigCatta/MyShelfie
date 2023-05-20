package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class GameStateObserver implements VirtualModelObserver {

    public GameState previousState;

    public GameStateObserver() {
        GameRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        if (previousState != null && previousState != GameRepresentation.getInstance().getGameState()) return;

        previousState = GameRepresentation.getInstance().getGameState();

        StageController.getController().updateGameState();
    }
}
