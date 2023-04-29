package it.polimi.ingsw.View.GUI.SceneController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class WaitingRoomController {
    @FXML
    Text playersText;
    @FXML
    Text playersNumText;

    @FXML
    public void onContinueButtonClick(ActionEvent actionEvent) {
        //start the game
    }

    @FXML
    public void onGoBackButtonClick(ActionEvent actionEvent) {
        //is it necessary?
    }
}
