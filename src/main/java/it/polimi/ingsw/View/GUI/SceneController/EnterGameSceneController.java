package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.CanIPlayMTS;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    private boolean connectPlayer;

    @Override
    public void updateEcho(EchoMTC echoMTC){
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
                wrongGameIdEffect();
                break;
        }
    }

    @FXML
    protected void onContinueButtonClick() {
        if(joinGameRB.isSelected()) {

            int gameId;
            try {
                gameId = Integer.parseInt(gameIdField.getText());
            } catch (NumberFormatException e) {
                wrongGameIdEffect();
                return;
            }

            SocketClient.getInstance().sendCommand(new CanIPlayMTS(gameId));
            //activates the possibility of entering a game after the server sends the echo join message
            connectPlayer = true;

        } else {
            //player wants to create a new game
            StageController.changeScene("fxml/player_number_scene.fxml","Set number of players");
        }
    }

    public void connectPlayer(){
        if(!connectPlayer) return;
        Platform.runLater(() -> StageController.changeScene("fxml/waiting_room.fxml", "Waiting room"));
    }

    public void enterGame(){
        Platform.runLater(() -> StageController.changeScene("fxml/board.fxml", "Board"));
    }

    public void connectionFailed(){
        connectPlayer = false;
        wrongGameIdEffect();
    }

    public void wrongGameIdEffect(){
        wrongGameIdImage.setVisible(true);
        //TODO set the text to show the error

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), wrongGameIdImage);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
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
