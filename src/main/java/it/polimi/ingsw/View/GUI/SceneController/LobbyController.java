package it.polimi.ingsw.View.GUI.SceneController;


import it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * this class is responsible for controlling the lobby scene in the GUI.
 */
public class LobbyController extends GuiController implements Initializable {

    private static boolean initialized;

    /**
     * Handles the event when the "Play" button is clicked.
     * Navigates to the login scene where the player can enter their information.
     */
    @FXML
    protected void onPlayButtonClick() {
        //player must insert his info
        Platform.runLater(() -> StageController.changeScene("fxml/login_scene.fxml", "Login")
        );

    }

    /**
     * Handles the event when the "Exit" button is clicked.
     * Exits the application.
     */
    @FXML
    protected void onExitButtonClick() {
        //end game
        Platform.exit();
        System.exit(0);
    }

    /**
     * Initializes the lobby scene.
     * Creates observers for the virtual model representations if not already initialized.
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (initialized) return;

        //create the observer linked to their relative Representation
        new GameObserver();
        new BoardObserver();
        new ChatObserver();
        new CommonGoalsObserver();
        new EchoObserver();
        new PlayerObserver();
        new ShelfObserver();
        new TilesTableObserver();

        initialized = true;
    }
}
