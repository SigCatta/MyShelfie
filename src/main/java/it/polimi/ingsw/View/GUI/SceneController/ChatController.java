package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChatController {
    @FXML
    TextArea newMessageField;
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

    List<TextField> myList;
    static int myMessagesCounter = 0;
    List<TextField> otherList;
    static int otherMessagesCounter = 0;

    @FXML
    public void init() {
        myList = new ArrayList<>(Arrays.asList(myText1, myText2, myText3, myText4, myText5));
        otherList = new ArrayList<>(Arrays.asList(otherText1, otherText2, otherText3, otherText4, otherText5));
    }

    /**
     * Method called every time a player writes on the chat
     * @param message the new message to be displayed
     * @param otherPlayer true if the message is from another player, false if is from this player
     */
    public void updateChat(String message, boolean otherPlayer) {
        if(otherMessagesCounter>=5 || myMessagesCounter>=5) {
            cleanChat();
        }
        if(otherPlayer) {
            for(TextField textField: otherList) {
                if(textField.getText().length() == 0) {
                    textField.setText(message);
                    textField.setVisible(true);
                    otherMessagesCounter++;
                    break;
                }
            }
        } else {
            for(TextField textField: myList) {
                if(textField.getText().length() == 0) {
                    textField.setText(message);
                    textField.setVisible(true);
                    myMessagesCounter++;
                    break;
                }
            }
        }
    }

    public void cleanChat() {
        for(TextField textField: myList) {
            textField.setText("");
            textField.setVisible(false);
        }
        for(TextField textField: otherList) {
            textField.setText("");
            textField.setVisible(false);
        }
        myMessagesCounter=0;
        otherMessagesCounter=0;
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

    @FXML
    public void onSendButtonClicked() {
        if(newMessageField.getText().length()>0) {
            //TODO send message to model

            updateChat(newMessageField.getText(), false);
            newMessageField.setText("");
        }
    }
}
