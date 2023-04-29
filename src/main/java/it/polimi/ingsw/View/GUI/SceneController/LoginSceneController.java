package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.View.GUI.Gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {
    @FXML
    TextField nicknameField;

    @FXML
    protected void onGoBackButtonClick() {
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
    protected void onContinueButtonClick() {
        //start game
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("waiting_room.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = StageController.getCurrentStage();
        stage.setTitle("Wait for others to join");
        stage.setScene(scene);
        stage.show();
    }
}
