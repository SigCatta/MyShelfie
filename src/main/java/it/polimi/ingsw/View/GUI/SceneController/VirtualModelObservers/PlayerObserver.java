package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.WaitingRoomController;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class PlayerObserver implements VirtualModelObserver {
    public PlayerObserver() {
        PlayersRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        if(WaitingRoomController.getInstance()!=null) {
            WaitingRoomController.getInstance().updatePlayersInfo(true);
        }
    }
}
