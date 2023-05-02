package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginSceneController {
    @FXML
    TextField nicknameField;

    @FXML
    TextField ipField;

    @FXML
    Button continueButton;

    @FXML
    protected void onGoBackButtonClick() {
        //back to the lobby
        StageController.changeScene("lobby.fxml","Lobby");
    }

    @FXML
    protected void onContinueButtonClick() {
        StageController.changeScene("game_info_scene.fxml","Set number of players");
    }

    @FXML
    public void setContinueButtonVisible() {
        if(nicknameField.getText().length()>0 )
            continueButton.setVisible(true);
    }

    public String getNickname() {
        return nicknameField.getText();
    }

    public String getIP() {
        return ipField.getText();
    }
}