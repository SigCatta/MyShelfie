package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.CLI.InputStates.StartOrJoinState;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginSceneController {
    @FXML
    TextField nicknameField;

    @FXML
    TextField ipField;

    @FXML
    Button continueButton;

    @FXML
    ImageView wrongNicknameImage;

    @FXML
    ImageView wrongIpImage;

    @FXML
    protected void onGoBackButtonClick() {
        //back to the lobby
        StageController.changeScene("fxml/lobby.fxml","Lobby");
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

        boolean correct;
        EchoMTC message = EchosRepresentation.getInstance().getMessage();
        if (message.isError()) {
            correct = false;
        } else {
            correct = true;
            SocketClient.getInstance().setNickname(getNickname());
        }
        isNicknameCorrect(correct);
    }

    @FXML
    public void setContinueButtonVisible() {
        if(nicknameField.getText().length()>0 )
            continueButton.setVisible(true);
    }

    public void isNicknameCorrect(boolean correct)  {
        //ckeck if nickname is valid
        if(correct) {
            //nickname is correct so change scene
            wrongNicknameImage.setVisible(false);
            StageController.changeScene("fxml/enter_game_scene.fxml","Set number of players");
        } else wrongNicknameImage.setVisible(true);
    }

    public void isIpCorrect(boolean correct)  {
        if(correct) {   //TODO ckeck if ip is valid
            wrongIpImage.setVisible(false);   //ip is correct
        } else wrongIpImage.setVisible(true);
    }

    public String getNickname() {
        return nicknameField.getText();
    }

    public String getIP() {
        return ipField.getText();
    }
}