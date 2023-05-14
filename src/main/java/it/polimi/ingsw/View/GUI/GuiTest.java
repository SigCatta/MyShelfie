package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Create new stage
        StageController.initStage();
        //load lobby
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("fxml/lobby.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.getIcons().add(new Image("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/Publisher_material/Icon 50x50px.png"));
        stage.setTitle("Board");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //set lobby as current stage
        StageController.setCurrentStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }


}