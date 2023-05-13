package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.CanIPlayMTS;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class EnterGameSceneController {
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
            //connect player to already existing game
            SocketClient.getInstance().sendCommand(new CanIPlayMTS(Integer.parseInt(gameIdField.getText())));

            new Thread(() -> {

                synchronized (EchosRepresentation.getInstance()) {
                    try {
                        EchosRepresentation.getInstance().wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                EchoMTC message = EchosRepresentation.getInstance().getMessage();
                if (message.isError()) {
                    checkGameId(false);
                    return;
                }
                checkGameId(true);
            }).start();

        } else {
            //player wants to create a new game
            StageController.changeScene("fxml/player_number_scene.fxml","Set number of players");
        }
    }

    public void checkGameId(boolean correct) {
        if(correct) {   //TODO ckeck if gameId is valid
            wrongGameIdImage.setVisible(false);   //gameId is correct

            //change scene based on the stage of the game
            GameState gameState = GameRepresentation.getInstance().getGameMessage().getGameState();
            if(gameState.equals(GameState.PREGAME)) {
                StageController.changeScene("fxml/waiting_room_new.fxml", "Waiting room");
            } else{
                StageController.changeScene("fxml/board.fxml", "Board");
            }

        } else wrongGameIdImage.setVisible(true);
    }

    @FXML
    protected void onJoinGameRBClicked() {
        gameIdField.setVisible(true);
        gameIdText.setVisible(true);
        setContinueButtonVisible();
    }

    @FXML
    protected void onNewGameRBClicked() {
        gameIdField.setVisible(false);
        gameIdText.setVisible(false);
        setContinueButtonVisible();
        wrongGameIdImage.setVisible(false);
    }
    @FXML
    public void setContinueButtonVisible() {
        if(joinGameRB.isSelected()) {
            if(gameIdField.getText().length()>0)
                continueButton.setVisible(true);
            else continueButton.setVisible(false);
        } else if(newGameRB.isSelected()){
            continueButton.setVisible(true);
        } else continueButton.setVisible(false);
    }

    public String getGameId() {
        return gameIdField.getText();
    }

    @FXML
    public void onGameIdInsert()  {
        wrongGameIdImage.setVisible(false);
        setContinueButtonVisible();
    }
}
