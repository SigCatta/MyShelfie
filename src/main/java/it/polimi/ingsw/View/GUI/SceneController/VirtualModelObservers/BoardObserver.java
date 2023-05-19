package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class BoardObserver implements VirtualModelObserver {

    public BoardObserver() {
        BoardRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        StageController.getController().updateBoard();
    }
}
