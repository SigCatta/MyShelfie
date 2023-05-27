package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * The GameObserver class is responsible for observing changes in the GameRepresentation class
 * and updating the game state in the GUI.
 */
public class GameObserver implements VirtualModelObserver {

    /**
     * Constructs a new GameObserver and registers it as an observer for changes in the GameRepresentation.
     */
    public GameObserver() {
        GameRepresentation.getInstance().registerObserver(this);
    }

    /**
     * This method is called when there is an update in the observed VirtualModel.
     * It notifies the StageController to update the game state in the GUI.
     */
    @Override
    public void update() {
        StageController.getController().updateGame();
    }
}