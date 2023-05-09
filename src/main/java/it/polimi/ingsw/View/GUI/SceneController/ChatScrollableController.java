package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.CanIPlayMTS;
import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.effect.ImageInput;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChatScrollableController {
    @FXML
    TextArea newMessageField;
    @FXML
    Button showBoardButton;
    @FXML
    Button showShelfButton;
    @FXML
    AnchorPane chatScrollPane;


    @FXML
    public void init() {
        //TODO fill chat with messages
    }

    /**
     * Method called every time a player writes on the chat
     * @param message the new message to be displayed
     * @param otherPlayer true if the message is from another player, false if is from this player
     */
    public void updateChat(String message, boolean otherPlayer) {
        TextField node = new TextField();
        node.setFont(new Font(14));
        if(otherPlayer) {
            node.setAlignment(Pos.BASELINE_LEFT);
            node.setText(message);
        } else {
            node.setText("Me - " + message);
            node.setAlignment(Pos.CENTER_RIGHT);
        }
        chatScrollPane.getChildren().add(node);
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
        String message = newMessageField.getText();
        if(message.length()>0) {
            //SocketClient.getInstance().sendCommand(new ChatMTS(message));

            updateChat(message, false);
            newMessageField.setText("");
        }
    }
}
