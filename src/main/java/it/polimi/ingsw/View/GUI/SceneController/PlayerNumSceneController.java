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


/**
 * This class is responsible for controlling the player number selection scene in the GUI.
 */
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

    /**
     * Updates the scene based on the received EchoMTC object.
     * @param echoMTC the EchoMTC object containing the update information
     */
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

    /**
     * Handles the "Continue" button click event.
     * Sends a NewGameMTS command to the server with the selected number of players.
     */
    @FXML
    protected void onContinueButtonClick() {
        if (continueClicked) return;

        //the new game has been created
        SocketClient.getInstance().sendCommand(new NewGameMTS(getPlayerNum()));
        continueClicked = true;
    }

    /**
     * Handles the "Back to Menu" button click event.
     * Changes the scene back to the login scene.
     */
    @FXML
    protected void onBackToMenuButtonClick() {
        Platform.runLater(() -> StageController.changeScene("fxml/enter_game_scene.fxml", "Login"));
    }

    /**
     * @return the selected number of players
     */
    public int getPlayerNum() {
        if (twoPlayers.isSelected()) {
            return 2;
        } else if (threePlayers.isSelected()) {
            return 3;
        } else
            return 4;
    }

    /**
     * Initializes the PlayerNumSceneController.
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        continueClicked = false;
    }
}
