package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        // Create new stage
        StageController.initStage();
        //load lobby
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("lobby.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Lobby");
        stage.setScene(scene);
        stage.show();
        //set lobby as current stage
        StageController.setCurrentStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
