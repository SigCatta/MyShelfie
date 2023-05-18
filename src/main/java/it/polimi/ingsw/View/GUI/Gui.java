package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        //load lobby
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("fxml/lobby.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        StageController.setUpStage(stage, scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}
