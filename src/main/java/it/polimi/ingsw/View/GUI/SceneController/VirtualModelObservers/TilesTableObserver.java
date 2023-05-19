package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.TilesTableRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class TilesTableObserver implements VirtualModelObserver {

    public TilesTableObserver() {
        TilesTableRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        StageController.getController().updateChosenTilesTable();
    }
}
