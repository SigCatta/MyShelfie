package it.polimi.ingsw.View.GUI.SceneController;

import javafx.stage.Stage;

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
}
