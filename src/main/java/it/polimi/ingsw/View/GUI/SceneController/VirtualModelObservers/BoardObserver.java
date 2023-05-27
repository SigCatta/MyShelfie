package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * The BoardObserver class is responsible for observing changes in the BoardRepresentation class and updating the board in the GUI.
 */
public class BoardObserver implements VirtualModelObserver {

    /**
     * Constructs a new BoardObserver and registers it as an observer for changes in the BoardRepresentation.
     */
    public BoardObserver() {
        BoardRepresentation.getInstance().registerObserver(this);
    }

    /**
     * This method is called when there is an update in the observed VirtualModel.
     * It notifies the StageController to update the board in the GUI.
     */
    @Override
    public void update() {
        StageController.getController().updateBoard();
    }
}