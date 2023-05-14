package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class ErrorObserver implements VirtualModelObserver {
    public ErrorObserver() {
        EchosRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        if (EchosRepresentation.getInstance().peekMessage().isError()) {
            BoardController.getInstance().updateError();
        }
    }
}
