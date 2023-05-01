package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.View.GUI.Gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerNumSceneController {
    @FXML
    ToggleGroup toggleGroup;
    /**
     * radio button for 2 players selected
     */
    @FXML
    RadioButton radioBtn1;
    /**
     * radio button for 3 players selected
     */
    @FXML
    RadioButton radioBtn2;
    /**
     * radio button for 4 players selected
     */
    @FXML
    RadioButton radioBtn3;

    @FXML
    protected void onContinueButtonClick() {
        //the new game has been created
        StageController.changeScene("waiting_room.fxml","Wait for others to join");
    }

    @FXML
    protected void onBackToMenuButtonClick() {
        StageController.changeScene("login_scene.fxml","Login");
    }

    public int getPlayerNum() {
        if(radioBtn1.isSelected()) {
            return 2;
        } else if(radioBtn2.isSelected()) {
            return 3;
        } else
            return 4;
    }
}
