package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Objects;


public class ChatController {
    @FXML
    TextField newMessageField;

    @FXML
    SplitMenuButton receiverMenu;

    @FXML
    MenuItem player2MenuItem;

    @FXML
    MenuItem player3MenuItem;

    @FXML
    MenuItem player4MenuItem;

    @FXML
    MenuItem broadcastMenuItem;

    static String receiverNickname = "";

    static int myMessagesCounter = 0;
    static int otherMessagesCounter = 0;

    /**
     * methods that sets up the names of the other player in the receiverMenu
     */
    @FXML
    public void init() {
        List<MenuItem> menuItemList = List.of(player2MenuItem, player3MenuItem, player4MenuItem);
        List<String> nicknames = PlayersRepresentation.getInstance().getPlayersList();
        int j=0;

        for(int i=0; i<nicknames.size(); i++) {
            if(!nicknames.get(i).equals(SocketClient.getInstance().getNickname())){
                menuItemList.get(j).setText(nicknames.get(i));
                j++;
            }
        }
    }

    /**
     * Method called every time a player writes on the chat
     * @param message the new message to be displayed
     * @param otherPlayer true if the message is from another player, false if is from this player
     * @param senderNickname if {@param otherPlayer} is true contains the nickname of the player that sent the message
     *                        else it contains a null string
     * @param receiverNickname if {@param otherPlayer} is false contains a null string
     *                         else it contains the nickname of the receiver of the message or "BROADCAST"
     */
    public void updateChat(String message, boolean otherPlayer, String senderNickname, String receiverNickname) {
        String newMessage;
        //TODO
        if(otherPlayer) {
            newMessage = senderNickname + " ~ " + message;

        } else {
            newMessage = "Me ~ " + message;
        }
    }

    public void cleanChat() {
        //TODO ?
        myMessagesCounter=0;
        otherMessagesCounter=0;
    }

    @FXML
    public void onBackToBoardClicked() {
        StageController.changeScene("fxml/board.fxml", "Board");
    }

    @FXML
    public void onSendButtonClicked() {
        String message = newMessageField.getText();
        if(message.length()>0) {
            if(receiverNickname.equals("")) receiverNickname = "BROADCAST";
            //TODO add receiver
            SocketClient.getInstance().sendCommand(new ChatMTS(message));

            updateChat(message, false, null, receiverNickname);
            newMessageField.setText("");
            receiverNickname = "";
        }
    }

    @FXML
    public void onBroadcastClicked() {
        receiverNickname = "BROADCAST";
    }

    @FXML
    public void onPlayer2Clicked() {
        receiverNickname = player2MenuItem.getText();
    }

    @FXML
    public void onPlayer3Clicked() {
        receiverNickname = player3MenuItem.getText();
    }

    @FXML
    public void onPlayer4Clicked() {
        receiverNickname = player4MenuItem.getText();
    }
}
