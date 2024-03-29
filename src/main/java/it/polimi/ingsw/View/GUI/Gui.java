package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A class that starts a javafx application with graphical interface
 */
public class Gui extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        StageController.setUpStage(stage, "fxml/lobby.fxml");

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}
