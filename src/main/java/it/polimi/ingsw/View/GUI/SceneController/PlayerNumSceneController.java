package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.NewGameMTS;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerNumSceneController extends GuiController implements Initializable {
    @FXML
    ToggleGroup playerNumToggle;
    /**
     * radio button for 2 players selected
     */
    @FXML
    RadioButton twoPlayers;
    /**
     * radio button for 3 players selected
     */
    @FXML
    RadioButton threePlayers;
    /**
     * radio button for 4 players selected
     */
    @FXML
    RadioButton fourPlayers;

    /**
     * this avoids the spamming of request to the server
     */
    private boolean continueClicked;

    @Override
    public void updateEcho(EchoMTC echoMTC) {
        switch (echoMTC.getID()) {
            case CREATED:
                Platform.runLater(() -> StageController.changeScene("fxml/waiting_room.fxml", "Wait for others to join"));
                break;
            case PANIC:
                continueClicked = false; //Should not happen
                break;
        }
    }

    @FXML
    protected void onContinueButtonClick() {
        if (continueClicked) return;

        //the new game has been created
        SocketClient.getInstance().sendCommand(new NewGameMTS(getPlayerNum()));
        continueClicked = true;
    }

    @FXML
    protected void onBackToMenuButtonClick() {
        Platform.runLater(() -> StageController.changeScene("fxml/enter_game_scene.fxml", "Login"));
    }

    public int getPlayerNum() {
        if (twoPlayers.isSelected()) {
            return 2;
        } else if (threePlayers.isSelected()) {
            return 3;
        } else
            return 4;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        continueClicked = false;
    }
}
