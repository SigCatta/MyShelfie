package it.polimi.ingsw.View.GUI.SceneController;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class DisconnectionErrorSceneController {

    @FXML
    protected void onExitButtonClick() {
        //end game
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void onLobbyButtonClick() {
        Platform.runLater(() -> StageController.changeScene("fxml/lobby.fxml","Login")
        );
    }

}
