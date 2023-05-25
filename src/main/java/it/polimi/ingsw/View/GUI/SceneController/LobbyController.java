package it.polimi.ingsw.View.GUI.SceneController;


import it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController extends GuiController implements Initializable {

    private static boolean initialized;

    @FXML
    protected void onPlayButtonClick() {
        //player must insert his info
        Platform.runLater(() -> StageController.changeScene("fxml/login_scene.fxml", "Login")
        );

    }

    @FXML
    protected void onExitButtonClick() {
        //end game
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (initialized) return;

        //create the observer linked to their relative Representation
        new GameObserver();
        new BoardObserver();
        new ChangeTurnObserver();
        new ChatObserver();
        new CommonGoalsObserver();
        new EchoObserver();
        new FirstPlayerToFinishObserver();
        new PlayerObserver();
        new ShelfObserver();
        new TilesTableObserver();

        initialized = true;
    }
}
