package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class ShelfObserver implements VirtualModelObserver {

    public ShelfObserver() {
        ShelvesRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        StageController.getController().updateShelf();
    }
}
