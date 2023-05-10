package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class GameInfoSceneController {
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
    protected void onContinueButtonClick() {
        if(joinGameRB.isSelected()) {
            //TODO: connect player to already existing game
        } else {
            //player want to create a new game
            //TODO: notify model
            StageController.changeScene("player_num_scene1.fxml","Set number of players");
        }
    }

    @FXML
    protected void onJoinGameRBClicked() {
        gameIdField.setVisible(true);
        gameIdText.setVisible(true);

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
        if(gameIdField.getText().length()>0) //TODO check if gameid is valid
            continueButton.setVisible(true);
        else continueButton.setVisible(false);
    }

    public String getGameId() {
        return gameIdField.getText();
    }

    @FXML
    public void onGameIdInsert()  {
        if(true) {   //TODO ckeck if gameId is valid
            wrongGameIdImage.setVisible(false);   //gameId is correct
            setContinueButtonVisible();
        } else wrongGameIdImage.setVisible(true);
    }
}
