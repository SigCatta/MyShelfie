package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.CanIPlayMTS;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * This class class is responsible for controlling the enter game scene in the GUI.
 */
public class EnterGameSceneController extends GuiController {
    @FXML
    ImageView wrongGameIdImage;

    @FXML
    RadioButton newGameRB;

    @FXML
    RadioButton joinGameRB;

    @FXML
    Text gameIdText;

    @FXML
    TextField gameIdField;

    @FXML
    Button continueButton;

    @FXML
    Text errorText;

    @FXML
    AnchorPane errorPane;

    private boolean connectPlayer;

    @Override
    public void updateEcho(EchoMTC echoMTC) {
        switch (echoMTC.getID()) {
            case JOINED:
                connectPlayer();
                break;
            case GAMESTARTED:
                enterGame();
                break;
            case GAMEFULL:
                connectionFailed();
                break;
            case NOID:
                wrongGameIdEffect(false);
                break;
        }
    }

    /**
     * Handles the continue button click event.
     */
    @FXML
    protected void onContinueButtonClick() {
        if (joinGameRB.isSelected()) {
            int gameId;
            try {
                gameId = Integer.parseInt(gameIdField.getText());
            } catch (NumberFormatException e) {
                wrongGameIdEffect(true);
                return;
            }

            SocketClient.getInstance().sendCommand(new CanIPlayMTS(gameId));
            //activates the possibility of entering a game after the server sends the echo join message
            connectPlayer = true;
        } else {
            //player wants to create a new game
            StageController.changeScene("fxml/player_number_scene.fxml", "Set number of players");
        }
    }

    /**
     * Connects the player to the game by changing the scene to the waiting room.
     */
    public void connectPlayer() {
        if (!connectPlayer) return;
        Platform.runLater(() -> StageController.changeScene("fxml/waiting_room.fxml", "Waiting room"));
    }

    /**
     * Enters the game by changing the scene to the game board.
     */
    public void enterGame() {
        Platform.runLater(() -> StageController.changeScene("fxml/board.fxml", "Board"));
    }

    /**
     *Handles the connection failure event by resetting the connectPlayer flag and displaying an error message.
     */
    public void connectionFailed() {
        connectPlayer = false;
        wrongGameIdEffect(false);
    }

    /**
     * Displays the wrong game ID error message.
     @param containsLetters true if the error is due to containing letters, false otherwise
     */
    public void wrongGameIdEffect(boolean containsLetters) {
        wrongGameIdImage.setVisible(true);
        errorText.setVisible(true);
        errorText.setWrappingWidth(300);

        if(containsLetters) {
            errorText.setText("The GAME ID must contain only numbers!");
        } else {
            errorText.setText(EchosRepresentation.getInstance().peekMessage().getOutput());
        }

        FadeTransition textFadeTransition = new FadeTransition(Duration.seconds(3), errorPane);
        textFadeTransition.setFromValue(1.0);
        textFadeTransition.setToValue(0.0);
        textFadeTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), wrongGameIdImage);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }

    /**
     * Handles the join game radio button click event.
     */
    @FXML
    public void onJoinGameRBClicked() {
        gameIdField.setVisible(true);
        gameIdText.setVisible(true);
        setContinueButtonVisible();
    }

    /**
     * Handles the new game radio button click event.
     */
    @FXML
    public void onNewGameRBClicked() {
        gameIdField.setVisible(false);
        gameIdText.setVisible(false);
        setContinueButtonVisible();
        wrongGameIdImage.setVisible(false);
    }

    /**
     * Sets the visibility of the continue button based on the selected radio button and the game ID field.
     */
    @FXML
    public void setContinueButtonVisible() {
        if (joinGameRB.isSelected()) {
            continueButton.setVisible(gameIdField.getText().length() > 0);
        } else {
            continueButton.setVisible(newGameRB.isSelected());
        }
    }

    /**
     * Handles the game ID insert event by hiding the wrong game ID image and updating the continue button visibility.
     */
    @FXML
    public void onGameIdInsert() {
        wrongGameIdImage.setVisible(false);
        setContinueButtonVisible();
    }
}