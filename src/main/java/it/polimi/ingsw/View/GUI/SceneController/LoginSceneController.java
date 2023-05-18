package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.animation.FadeTransition;
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

public class LoginSceneController implements Initializable {
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
    ImageView wrongIpImage;
    @FXML
    Text wrongIpText;
    @FXML
    StackPane login_scene;

    @FXML
    protected void onGoBackButtonClick() {
        //back to the lobby
        StageController.changeScene("fxml/lobby.fxml", "Lobby");
    }

    @FXML
    protected void onContinueButtonClick() {
        SocketClient.getInstance().sendCommand(new HandshakeMTS(getNickname()));

        synchronized (EchosRepresentation.getInstance()) {
            try {
                EchosRepresentation.getInstance().wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        EchoMTC message = EchosRepresentation.getInstance().popMessage();
        if (message.getID() == EchoID.NICKOK) {
            SocketClient.getInstance().setNickname(getNickname());
            StageController.changeScene("fxml/enter_game_scene.fxml", "Set number of players");
        } else if (message.getID() == EchoID.BADNICK) {
            nicknameErrorPane.setVisible(true);
            wrongNicknameText.setWrappingWidth(200);

            wrongNicknameText.setText(message.getOutput());
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), nicknameErrorPane);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.play();
        }
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

    public void isIpCorrect(boolean correct) {
        if (correct) {   //TODO ckeck if ip is valid
            wrongIpImage.setVisible(false);   //ip is correct
        } else wrongIpImage.setVisible(true);
    }

    public String getNickname() {
        return nicknameField.getText();
    }

    public String getIP() {
        return ipField.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_scene.setOnKeyPressed(this::onKeyPressed);
    }
}