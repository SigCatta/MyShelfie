package it.polimi.ingsw.View.GUI.SceneController;


import javafx.application.Platform;
import javafx.fxml.FXML;

public class LobbyController {
    @FXML
    protected void onPlayButtonClick() {
        //player must insert his info
        StageController.changeScene("fxml/login_scene.fxml","Login");
    }

    @FXML
    protected void onExitButtonClick() {
        //end game
        Platform.exit();
    }
}
