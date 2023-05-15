package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WaitingRoomController {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    @FXML
    Text playersNamesText;
    @FXML
    Text maxNumText;

    @FXML
    Text currentNumText;

    @FXML
    TextField gameIdText;

    @FXML
    Button continueButton;

    @FXML
    public void onContinueButtonClick(ActionEvent actionEvent) {
        //start the game
        StageController.changeScene("fxml/board.fxml","Living room");
    }

    /*
    <Button layoutX="107.0" layoutY="524.0" mnemonicParsing="false" onAction="#onGoBackButtonClick" prefHeight="64.0" prefWidth="269.0" styleClass="join_button" stylesheets="@../css/join_game_scene.css" text="GO BACK">
                           <font>
                              <Font name="Arial Black" size="18.0" />
                           </font>
                        </Button>
    @FXML
    public void onGoBackButtonClick(ActionEvent actionEvent) {
        SocketClient.getInstance().sendCommand(new ByeMTS());
        StageController.changeScene("fxml/enter_game_scene.fxml","Login");
    }
     */

    public void setContinueButtonVisible() {
        continueButton.setVisible(true);
    }

    public void updatePlayersNamesText() {
        playersNamesText.setText("");
        for(String name: PlayersRepresentation.getInstance().getPlayersList()) {
            playersNamesText.setText(name + ", " + playersNamesText.getText());
        }
    }

    public void setMaxNumText(int num) {
        maxNumText.setText(String.valueOf(num));
    }

    public void updateCurrentNumText() {
        int num = PlayersRepresentation.getInstance().getPlayersList().size();
        currentNumText.setText(String.valueOf(num));
        if(num == Integer.parseInt(maxNumText.getText())) {
            setContinueButtonVisible();
        }
    }

    @FXML
    public void setUp() {
        executor.submit(() -> {
            while (GameRepresentation.getInstance().getGameMessage() == null) {
                synchronized (GameRepresentation.getInstance()) {
                    StageController.waitForVMReprensentation(GameRepresentation.getInstance());
                }
            }
            updatePlayersInfo(false);

            while (GameRepresentation.getInstance().getGameMessage().getActivePlayerNickname() == null) {
                updatePlayersInfo(true);
                synchronized (GameRepresentation.getInstance()) {
                    StageController.waitForVMReprensentation(GameRepresentation.getInstance());
                }
            }
            updatePlayersInfo(true);
        });
    }

    private void updatePlayersInfo(boolean gameCreated) {
        if(!gameCreated) {
            setMaxNumText(GameRepresentation.getInstance().getMAX_PLAYER_NUMBER());
            gameIdText.setText(String.valueOf(GameRepresentation.getInstance().getGameID()));
            gameIdText.setAccessibleText(String.valueOf(GameRepresentation.getInstance().getGameID()));
        } else {
            updatePlayersNamesText();
            updateCurrentNumText();
        }
    }
}
