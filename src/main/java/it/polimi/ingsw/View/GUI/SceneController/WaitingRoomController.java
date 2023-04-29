package it.polimi.ingsw.View.GUI.SceneController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class WaitingRoomController {
    @FXML
    Text playersText;
    @FXML
    Text playersNumText;

    @FXML
    Button continueButton;

    @FXML
    public void onContinueButtonClick(ActionEvent actionEvent) {
        //start the game
        StageController.changeScene("board.fxml","Living room");
    }

    @FXML
    public void onGoBackButtonClick(ActionEvent actionEvent) {
        StageController.changeScene("login_scene.fxml","Login");
    }

    public void setContinueButtonVisible() {
        continueButton.setVisible(true);
    }
}
