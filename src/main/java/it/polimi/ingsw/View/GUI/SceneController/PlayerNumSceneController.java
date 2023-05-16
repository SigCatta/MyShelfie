package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.NewGameMTS;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PlayerNumSceneController {
    @FXML
    ToggleGroup playerNumToggle;
    /**
     * radio button for 2 players selected
     */
    @FXML
    RadioButton twoPlayers;
    /**
     * radio button for 3 players selected
     */
    @FXML
    RadioButton threePlayers;
    /**
     * radio button for 4 players selected
     */
    @FXML
    RadioButton fourPlayers;

    @FXML
    protected void onContinueButtonClick() {
        //the new game has been created
        SocketClient.getInstance().sendCommand(new NewGameMTS(getPlayerNum()));
        StageController.changeScene("fxml/waiting_room_new.fxml","Wait for others to join");
    }

    @FXML
    protected void onBackToMenuButtonClick() {
        StageController.changeScene("fxml/enter_game_scene.fxml", "Login");
    }

    public int getPlayerNum() {
        if(twoPlayers.isSelected()) {
            return 2;
        } else if(threePlayers.isSelected()) {
            return 3;
        } else
            return 4;
    }
}
