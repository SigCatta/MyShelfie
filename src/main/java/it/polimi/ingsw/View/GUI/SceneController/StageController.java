package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.View.GUI.Gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the current stage used to play the game.
 */
public class StageController {
    private static Stage currentStage = new Stage();
    private static GuiController controller;
    public static Scene activeScene;

    public static void setUpStage(Stage stage, String FXMLScene) throws IOException {

        //load lobby
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource(FXMLScene));
        Scene scene = new Scene(fxmlLoader.load());

        controller = fxmlLoader.getController();
        currentStage = stage;
        activeScene = scene;

        stage.getIcons().add(new Image("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/Publisher_material/Icon 50x50px.png"));
        stage.setTitle("Lobby");
        stage.setScene(scene);
    }

    public static GuiController getController() {
        return controller;
    }

    public static void changeScene(String FXMLScene, String title) {

        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource(FXMLScene));

        try {
            Parent root = fxmlLoader.load();
            activeScene.setRoot(root);
            controller = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentStage.setTitle(title);
        currentStage.setOnCloseRequest(event -> System.exit(0));
        currentStage.show();
    }

}
