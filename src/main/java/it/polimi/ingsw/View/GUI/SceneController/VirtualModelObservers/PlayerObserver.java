package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * The PlayerObserver class is responsible for observing changes in the PlayersRepresentation class and updating the players in the GUI.
 */
public class PlayerObserver implements VirtualModelObserver {

    /**
     * Constructs a new PlayerObserver and registers it as an observer for changes in the PlayersRepresentation.
     */
    public PlayerObserver() {
        PlayersRepresentation.getInstance().registerObserver(this);
    }

    /**
     * This method is called when there is an update in the observed VirtualModel.
     * It notifies the StageController to update the players in the GUI.
     */
    @Override
    public void update() {
        StageController.getController().updatePlayers();
    }
}