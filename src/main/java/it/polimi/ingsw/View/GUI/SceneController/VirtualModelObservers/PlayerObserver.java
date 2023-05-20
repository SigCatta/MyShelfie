package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import javafx.application.Platform;

import java.util.ArrayList;

public class PlayerObserver implements VirtualModelObserver {
    public PlayerObserver() {
        PlayersRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        ArrayList<PlayerMTC> messages = PlayersRepresentation.getInstance().getAllPlayerMTC();
        for (PlayerMTC p : messages) {
            if (!p.isConnected()) {
                Platform.runLater(() -> StageController.changeScene("fxml/disconnection_error.fxml","Disconnection error")
                );
                return;
            }
        }

        StageController.getController().updatePlayers();
    }
}
