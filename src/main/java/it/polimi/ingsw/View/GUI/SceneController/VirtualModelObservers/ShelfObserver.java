package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class ShelfObserver implements VirtualModelObserver {

    public ShelfObserver() {
        ShelvesRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        BoardController.getInstance().updateShelf(); //TODO This updates the shelf of the player even if another player changes his shelf
    }
}
