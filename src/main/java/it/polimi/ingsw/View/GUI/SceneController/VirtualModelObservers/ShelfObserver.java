package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * The ShelfObserver class is responsible for observing changes in the ShelvesRepresentation class
 * and updating the shelf in the GUI.
 */
public class ShelfObserver implements VirtualModelObserver {

    /**
     * Constructs a new ShelfObserver and registers it as an observer for changes in the ShelvesRepresentation.
     */
    public ShelfObserver() {
        ShelvesRepresentation.getInstance().registerObserver(this);
    }

    /**
     * This method is called when there is an update in the observed VirtualModel.
     * It notifies the StageController to update the shelf in the GUI.
     */
    @Override
    public void update() {
        StageController.getController().updateShelf();
    }
}