package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class PlayerObserver implements VirtualModelObserver {
    public PlayerObserver() {
        PlayersRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        StageController.getController().updatePlayers();
    }
}
