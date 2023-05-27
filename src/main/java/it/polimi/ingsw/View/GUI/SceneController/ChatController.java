package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ChatMemory;
import it.polimi.ingsw.VirtualModel.ChatRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualView.Messages.ChatMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.*;

/**
 * This class is responsible for managing the chat functionality in the GUI.
 */
public class ChatController extends GuiController implements Initializable {
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

    @FXML
    StackPane chat_scene;

    static String receiverNickname = "";

    /**
     * Initializes the chat view.
     */
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

    /**
     * Updates the game state.
     */
    @Override
    public void updateGame() {
        if (GameRepresentation.getInstance().getGameState() == GameState.END) {
            Platform.runLater(() -> StageController.changeScene("fxml/win_scene.fxml", "Game Finished"));
        }
    }

    /**
     * Updates the chat messages.
     */
    @Override
    public void updateChat() {
        ArrayList<ChatMTC> messages = ChatRepresentation.getInstance().getMessages();
        int size = messages.size();
        int i = 0, index;
        String header;
        ChatMemory.clear();

        for (int row = chat.getRowCount() - 1; row >= 0; row--) {
            index = size - 1 - i;
            if (index < 0) return;
            ChatMTC message = messages.get(index);
            int col;
            if (message.getSender().equals(SocketClient.getInstance().getNickname())) {
                col = 1;
            } else {
                col = 0;
            }
            if (message.isBroadcast()) {
                header = message.getSender() + " ~ ";
            } else {
                header = message.getSender() + " to " + message.getRECEIVER() + " ~ ";
            }

            ChatMemory.setMessage(header + message.getChatMessage(), row, col);
            i++;
        }
    }

    /**
     * Handles the event when the "Back to Board" button is clicked.
     */
    @FXML
    public void onBackToBoardClicked() {
        StageController.changeScene("fxml/board.fxml", "Board");
    }

    /**
     * Handles the event when the "Send" button is clicked.
     */
    @FXML
    public void onSendButtonClicked() {
        String message = newMessageField.getText();
        if (message.length() > 0) {
            if (receiverNickname.equals("BROADCAST") || receiverNickname.equals("")) receiverNickname = null;
            SocketClient.getInstance().sendCommand(new ChatMTS(message, receiverNickname));

            newMessageField.setText("");
            receiverNickname = "";
        }
    }

    private boolean enterKeyBusy;

    /**
     * Handles the key pressed event.
     *
     * @param keyEvent the KeyEvent object
     */
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            if (enterKeyBusy) return;

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    enterKeyBusy = false;
                    timer.cancel();
                }
            }, 400, 400);

            onSendButtonClicked();
            enterKeyBusy = true;
        }
    }

    /**
     * Handles the event when the "Broadcast" menu item is clicked.
     */
    @FXML
    public void onBroadcastClicked() {
        receiverNickname = "BROADCAST";
    }

    /**
     * Handles the event when the "Player2" menu item is clicked.
     */
    @FXML
    public void onPlayer2Clicked() {
        receiverNickname = player2MenuItem.getText();
    }

    /**
     * Handles the event when the "Player3" menu item is clicked.
     */
    @FXML
    public void onPlayer3Clicked() {
        receiverNickname = player3MenuItem.getText();
    }

    /**
     * Handles the event when the "Player4" menu item is clicked.
     */
    @FXML
    public void onPlayer4Clicked() {
        receiverNickname = player4MenuItem.getText();
    }

    /**
     * Initializes the nicknames in the menu that lets you select the receiver of the message.
     */
    private void initNickname() {
        List<MenuItem> menuItemList = List.of(player2MenuItem, player3MenuItem, player4MenuItem);
        List<String> nicknames = PlayersRepresentation.getInstance().getPlayersList();
        int j = 0;

        for (String nickname : nicknames) {
            if (!nickname.equals(SocketClient.getInstance().getNickname())) {
                menuItemList.get(j).setText(nickname);
                menuItemList.get(j).setVisible(true);
                j++;
            }
        }
    }

    /**
     * Initializes the controller and sets the listener for the key pressed event.
     * @param url            the URL location
     * @param resourceBundle the ResourceBundle used for localization
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChat();
        updateChat();
        initNickname();
        chat_scene.setOnKeyPressed(this::onKeyPressed);
    }
}