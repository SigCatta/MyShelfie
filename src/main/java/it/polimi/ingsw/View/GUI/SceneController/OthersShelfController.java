package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;



public class OthersShelfController {
    @FXML
    GridPane matrix;

    @FXML
    Text playerName;

    @FXML
    Button nextShelfButton;

    @FXML
    Button prevShelfButton;

    @FXML
    Button exitButton;


    public void setPlayerName(Text playerName) {
        this.playerName = playerName;
    }
}
