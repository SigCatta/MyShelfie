package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.ByeMTS;
import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.CLI.InputStates.GameStartupState;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class WaitingRoomController {
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

    public void updatePlayersNamesText(ArrayList<String> names) {
        for(String name: names) {
            playersNamesText.setText(name + ", ");
        }
    }

    public void setMaxNumText(int num) {
        maxNumText.setText(String.valueOf(num));
    }

    public void updateCurrentNumText(int num) {
        currentNumText.setText(String.valueOf(num));
        if(num == Integer.parseInt(maxNumText.getText())) {
            setContinueButtonVisible();
        }
    }

    @FXML
    public void setUp() {
        setMaxNumText(GameRepresentation.getInstance().getMAX_PLAYER_NUMBER());
        gameIdText.setText(String.valueOf(GameRepresentation.getInstance().getGameID()));
        gameIdText.setAccessibleText(String.valueOf(GameRepresentation.getInstance().getGameID()));

        new Thread(() -> {
            while (GameRepresentation.getInstance().getGameMessage().getActivePlayerNickname() == null) {
                updatePlayersNamesText(PlayersRepresentation.getInstance().getPlayersList());
                updateCurrentNumText(PlayersRepresentation.getInstance().getPlayersList().size());
                synchronized (GameRepresentation.getInstance()) {
                    try {
                        GameRepresentation.getInstance().wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            updateCurrentNumText(PlayersRepresentation.getInstance().getPlayersList().size());
        }).start();
    }
}
