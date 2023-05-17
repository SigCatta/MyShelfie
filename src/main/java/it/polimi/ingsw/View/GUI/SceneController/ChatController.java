package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ChatMemory;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ShelfMemory;
import it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers.ChatObserver;
import it.polimi.ingsw.VirtualModel.ChatRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualView.Messages.ChatMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class ChatController implements Initializable {
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

    @FXML
    GridPane chat;

    static String receiverNickname = "";

    private static ChatController instance;

    public ChatController() {
        instance = this;
    }

    public static ChatController getInstance() {
        return instance;
    }

    /**
     * methods that sets up the names of the other player in the receiverMenu ad retrieves the messages already sent
     */
    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChat();
        List<MenuItem> menuItemList = List.of(player2MenuItem, player3MenuItem, player4MenuItem);
        List<String> nicknames = PlayersRepresentation.getInstance().getPlayersList();
        int j=0;

        for(int i=0; i<nicknames.size(); i++) {
            if(!nicknames.get(i).equals(SocketClient.getInstance().getNickname())){
                menuItemList.get(j).setText(nicknames.get(i));
                menuItemList.get(j).setVisible(true);
                j++;
            }
        }
        new ChatObserver().update();
    }

    public void initChat() {
        for (int row = 0; row < chat.getRowCount(); row++) {
            for (int col = 0; col < chat.getColumnCount(); col++) {
                if (ChatMemory.getMessage(row, col) == null) {
                    TextField textField = new TextField();
                    ChatMemory.setMessage(textField, row, col);
                }
                chat.add(ChatMemory.getMessage(row, col), col, row);
            }
        }
    }

    public void updateChat() {
        ArrayList<ChatMTC> messages = ChatRepresentation.getInstance().getMessages();
        int size = messages.size();
        int i=0, index;
        String header;
        ChatMemory.clear();

        for (int row = chat.getRowCount()-1; row >= 0; row--) {
            index = size - 1 - i;
            if(index<0) return;
            ChatMTC message = messages.get(index);
            int col;
            if(message.getSender().equals(SocketClient.getInstance().getNickname())) {
                col=1;
            } else {
                col = 0;
            }
            if(message.isBroadcast()) {
                header = message.getSender() +  " ~ " ;
            } else {
                header = message.getSender() + " to " + message.getRECEIVER() +  " ~ ";
            }

            ChatMemory.setMessage(header + message.getChatMessage(), row, col);
            i++;
        }
    }

    @FXML
    public void onBackToBoardClicked() {
        StageController.changeScene("fxml/board.fxml", "Board");
    }

    @FXML
    public void onSendButtonClicked() {
        String message = newMessageField.getText();
        if(message.length()>0) {
            if(receiverNickname.equals("BROADCAST") || receiverNickname.equals("")) receiverNickname = null;
            SocketClient.getInstance().sendCommand(new ChatMTS(message, receiverNickname));

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
