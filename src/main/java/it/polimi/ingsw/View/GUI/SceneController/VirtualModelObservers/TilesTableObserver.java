package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.VirtualModel.TilesTableRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class TilesTableObserver implements VirtualModelObserver {

    public TilesTableObserver() {
        TilesTableRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        BoardController.getInstance().updateChosenTilesTable();
    }
}
