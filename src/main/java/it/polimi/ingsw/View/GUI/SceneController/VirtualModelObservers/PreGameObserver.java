package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.WaitingRoomController;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class PreGameObserver implements VirtualModelObserver {
    boolean gameCreated;
    public PreGameObserver() {
        gameCreated = false;
        GameRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        if (GameRepresentation.getInstance().getGameMessage() == null) {
            gameCreated = false;
            return;
        }

        callPlayerObserver();
        WaitingRoomController.getInstance().updatePlayersInfo(false);
    }

    private void callPlayerObserver() {
        if(!gameCreated) {
            gameCreated = true;
            new PlayerObserver().update();
        }
    }
}
