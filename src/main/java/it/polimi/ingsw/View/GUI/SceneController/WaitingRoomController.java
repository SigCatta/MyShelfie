package it.polimi.ingsw.View.GUI.SceneController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class WaitingRoomController {
    @FXML
    Text playersNamesText;
    @FXML
    Text maxNumText;

    @FXML
    Text currentNumText;

    @FXML
    Button continueButton;

    @FXML
    public void onContinueButtonClick(ActionEvent actionEvent) {
        //start the game
        StageController.changeScene("fxml/board.fxml","Living room");
    }

    @FXML
    public void onGoBackButtonClick(ActionEvent actionEvent) {
        StageController.changeScene("fxml/login_scene.fxml","Login");
    }

    public void setContinueButtonVisible() {
        continueButton.setVisible(true);
    }

    public void updatePlayersNamesText(String name) {
        playersNamesText.setText(name + ", " + playersNamesText.getText());
    }

    public void setMaxNumText(int num) {
        maxNumText.setText(String.valueOf(num));
    }

    public void updateCurrentNumText() {
        currentNumText.setText(String.valueOf(Integer.parseInt(currentNumText.getText())+1));
    }
}
