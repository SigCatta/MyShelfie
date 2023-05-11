package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.View.GUI.Gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the current stage used to play the game.
 */
public class StageController {
    private static Stage currentStage;

    public static void initStage() {
        if(currentStage==null) {
            currentStage = new Stage();
        }
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        StageController.currentStage = currentStage;
    }

    public static void changeScene(String FXMLScene, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource(FXMLScene));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = getCurrentStage();
        stage.setTitle(title);
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.setFullScreen(true);

        stage.show();
    }
}
