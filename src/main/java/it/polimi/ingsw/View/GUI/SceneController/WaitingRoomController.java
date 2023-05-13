package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.CLI.InputStates.GameStartupState;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class WaitingRoomController {
    @FXML
    Text playersNamesText;
    @FXML
    Text maxNumText;

    @FXML
    Text currentNumText;

    @FXML
    Button continueButton;

    @FXML
    public void onContinueButtonClick(ActionEvent actionEvent) {
        //start the game
        StageController.changeScene("fxml/board.fxml","Living room");
    }

    @FXML
    public void onGoBackButtonClick(ActionEvent actionEvent) {
        StageController.changeScene("fxml/login_scene.fxml","Login");
    }

    public void setContinueButtonVisible() {
        continueButton.setVisible(true);
    }

    public void updatePlayersNamesText(ArrayList<String> names) {
        for(String name: names) {
            playersNamesText.setText(name + ", " + playersNamesText.getText());
        }
    }

    public void setMaxNumText(int num) {
        maxNumText.setText(String.valueOf(num));
    }

    public void updateCurrentNumText(int num) {
        currentNumText.setText(String.valueOf(num));
        if(num == Integer.parseInt(maxNumText.getText())) {
            setContinueButtonVisible();
        }
    }

    @FXML
    public void setUp() {
        setMaxNumText(GameRepresentation.getInstance().getMAX_PLAYER_NUMBER());

        new Thread(() -> {
            while (GameRepresentation.getInstance().getGameMessage().getActivePlayerNickname() == null) {
                updatePlayersNamesText(PlayersRepresentation.getInstance().getPlayersList());
                updateCurrentNumText(PlayersRepresentation.getInstance().getPlayersList().size());
                synchronized (GameRepresentation.getInstance()) {
                    try {
                        GameRepresentation.getInstance().wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
