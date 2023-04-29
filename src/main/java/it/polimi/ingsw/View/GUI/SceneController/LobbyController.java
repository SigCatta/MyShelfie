package it.polimi.ingsw.View.GUI.SceneController;


import it.polimi.ingsw.View.GUI.Gui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LobbyController {
    @FXML
    protected void onPlayButtonClick() {
        //player must insert his info
        StageController.changeScene("login_scene.fxml","Login");
    }

    @FXML
    protected void onExitButtonClick() {
        //end game
        Platform.exit();
    }
}
