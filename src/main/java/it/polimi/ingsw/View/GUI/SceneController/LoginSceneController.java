package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.InputValidator;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ConnectionPendingTimer;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginSceneController extends GuiController implements Initializable {

    @FXML
    TextField nicknameField;

    @FXML
    TextField ipField;

    @FXML
    ImageView continueButton;

    @FXML
    Label continueText;

    @FXML
    ImageView wrongNicknameImage;
    @FXML
    Text wrongNicknameText;

    @FXML
    AnchorPane nicknameErrorPane;
    @FXML
    AnchorPane ipErrorPane;
    @FXML
    ImageView wrongIpImage;
    @FXML
    Text wrongIpText;
    @FXML
    StackPane login_scene;

    @Override
    public void updateEcho(EchoMTC echoMTC) {
        switch (echoMTC.getID()) {
            case BADNICK:
                badNicknameEffect(echoMTC);
                break;
            case NICKOK:
                goToCreateGame();
                break;
        }
    }


    @FXML
    protected void onGoBackButtonClick() {
        //back to the lobby
        Platform.runLater(() -> StageController.changeScene("fxml/lobby.fxml", "Lobby"));
    }

    @FXML
    protected void onContinueButtonClick() {
        if (ConnectionPendingTimer.isPending()) return;

        if (!InputValidator.isValidIpAddress(ipField.getText())) {
            showCouldNotConnectMessage();
            return;
        }

        Client client;
        try {
            client = SocketClient.getInstance(ipField.getText(), 28888);
            client.readCommand(); // Starts an asynchronous reading from the server.
        } catch (Exception e) {
            showCouldNotConnectMessage();
            return;
        }

        SocketClient.getInstance().sendCommand(new HandshakeMTS(nicknameField.getText()));

        ConnectionPendingTimer.start(1);
    }


    @FXML
    public void setContinueButtonVisible() {
        if (nicknameField.getText().length() > 0 && nicknameField.getText().length() < 30) {
            continueButton.setVisible(true);
            continueText.setVisible(true);
        } else {
            continueButton.setVisible(false);
            continueText.setVisible(false);
        }

    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onContinueButtonClick();
        }
    }

    /**
     * if the ConnectionPendingTimer expires show this message
     */
    private void showCouldNotConnectMessage() {
        ipErrorPane.setVisible(true);
        wrongIpImage.setVisible(true);
        wrongIpImage.setVisible(true);
        wrongIpText.setText("Can't connect to the server...");

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), ipErrorPane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }

    private void badNicknameEffect(EchoMTC message){
        nicknameErrorPane.setVisible(true);
        wrongNicknameText.setWrappingWidth(200);

        wrongNicknameText.setText(message.getOutput());
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), nicknameErrorPane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }

    private void goToCreateGame(){
        if(ConnectionPendingTimer.isPending()){
            ConnectionPendingTimer.cancel();
        }
        SocketClient.getInstance().setNickname(nicknameField.getText());
        Platform.runLater(() -> StageController.changeScene("fxml/enter_game_scene.fxml", "Set number of players"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_scene.setOnKeyPressed(this::onKeyPressed);
        ipField.setText("localhost");
    }
}