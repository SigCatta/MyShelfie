package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.TilesTableRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * The TilesTableObserver class is responsible for observing changes in the TilesTableRepresentation class and updating the chosen tiles table in the GUI.
 */
public class TilesTableObserver implements VirtualModelObserver {

    /**
     * Constructs a new TilesTableObserver and registers it as an observer for changes in the TilesTableRepresentation.
     */
    public TilesTableObserver() {
        TilesTableRepresentation.getInstance().registerObserver(this);
    }

    /**
     * This method is called when there is an update in the observed VirtualModel.
     * It notifies the StageController to update the chosen tiles table in the GUI.
     */
    @Override
    public void update() {
        StageController.getController().updateChosenTilesTable();
    }
}