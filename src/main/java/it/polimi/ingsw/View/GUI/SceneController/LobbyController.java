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
        //start game
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("player_num_scene.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = StageController.getCurrentStage();
        stage.setTitle("Set number of players");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onExitButtonClick() {
        //end game
        Platform.exit();
    }
}
