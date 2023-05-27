package it.polimi.ingsw.View.GUI.SceneController;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * This class is responsible for controlling the disconnection error scene in the GUI.
 */
public class DisconnectionErrorSceneController {

    /**
     * Handles the exit button click event.
     */
    @FXML
    protected void onExitButtonClick() {
        //end game
        Platform.exit();
        System.exit(0);
    }

    /**
     * Handles the lobby button click event.
     */
    @FXML
    protected void onLobbyButtonClick() {
        Platform.runLater(() -> StageController.changeScene("fxml/lobby.fxml", "Login"));
    }
}
