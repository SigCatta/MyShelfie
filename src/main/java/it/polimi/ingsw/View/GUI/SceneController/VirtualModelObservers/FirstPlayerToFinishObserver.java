package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class FirstPlayerToFinishObserver implements VirtualModelObserver {


    public FirstPlayerToFinishObserver() {
        EchosRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        if (EchosRepresentation.getInstance().peekMessage().getID() != EchoID.LAST_TURN) return;
        if (BoardController.getInstance() == null) return;

        BoardController.getInstance().updateFirstFinishPlayer();
    }
}
