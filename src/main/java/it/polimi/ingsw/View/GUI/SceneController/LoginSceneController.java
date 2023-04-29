package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.View.GUI.Gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {
    @FXML
    TextField nicknameField;

    @FXML
    TextField ipField;

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
    protected void onGoBackButtonClick() {
        //back to the lobby
        StageController.changeScene("lobby.fxml","Lobby");
    }

    @FXML
    protected void onContinueButtonClick() {
        if(joinGameRB.isSelected()) {
            //TODO: connect player to already existing game
        } else {
            //player want to create a new game
            StageController.changeScene("player_num_scene.fxml","Set number of players");
        }
    }

    @FXML
    protected void onJoinGameRBClicked() {
        gameIdField.setVisible(true);
        gameIdText.setVisible(true);
        setContinueButtonVisible();

        //TODO: let player join the game
    }

    @FXML
    protected void onNewGameRBClicked() {
        gameIdField.setVisible(false);
        gameIdText.setVisible(false);
        setContinueButtonVisible();

        //TODO: let player start the game
    }
    @FXML
    public void setContinueButtonVisible() {
        System.out.println(nicknameField.getCharacters());
        System.out.println(ipField.getCharacters());
        if(nicknameField.getText().length()>0 && (newGameRB.isSelected() || joinGameRB.isSelected()))
            continueButton.setVisible(true);
    }
}
