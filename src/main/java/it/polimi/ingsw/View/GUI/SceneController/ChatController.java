package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChatController {
    @FXML
    Button showBoardButton;
    @FXML
    Button showShelfButton;
    @FXML
    TextField myText1;
    @FXML
    TextField otherText1;
    @FXML
    TextField myText2;
    @FXML
    TextField otherText2;
    @FXML
    TextField myText3;
    @FXML
    TextField otherText3;
    @FXML
    TextField myText4;
    @FXML
    TextField otherText4;
    @FXML
    TextField myText5;
    @FXML
    TextField otherText5;

    List<TextField> myList = new ArrayList<>(Arrays.asList(myText1, myText2, myText3, myText4, myText5));
    List<TextField> otherList = new ArrayList<>(Arrays.asList(otherText1, otherText2, otherText3, otherText4, otherText5));

    /*
    otherText:
    <TextField layoutX="174.0" layoutY="58.0" prefHeight="39.0" prefWidth="132.0" style="-fx-background-color: lightblue;" text="">
         <font>
            <Font name="Gill Sans MT" size="16.0" />
         </font>
      </TextField>

      myText:
      <TextField layoutX="530.0" layoutY="58.0" prefHeight="39.0" prefWidth="132.0" style="-fx-background-color: lightgreen;" text="">
         <font>
            <Font name="Gill Sans MT" size="16.0" />
         </font>
     */

    /**
     * Method called every time a player writes on the chat
     * @param message the new message to be displayed
     * @param otherPlayer true if the message is from another player, false if is from this player
     */
    public void updateChat(String message, boolean otherPlayer) {
        //TODO: set visible the correct textField and write text

    }

    public void setButtonVisible() {
        if(true ) { //TODO: call call that check at which stage of game we are
            showBoardButton.setVisible(true);
        } else {
            showShelfButton.setVisible(true);
        }
    }

    @FXML
    public void onShowMyShelfClicked() {
        StageController.changeScene("shelf.fxml", "My shelf");
    }

    @FXML
    public void onShowBoardClicked() {
        StageController.changeScene("board.fxml", "Board");
    }
}
