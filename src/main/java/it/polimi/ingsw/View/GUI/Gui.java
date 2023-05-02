package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.model.tiles.Color;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashMap;

public class Gui extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        // Create new stage
        StageController.initStage();
        //load lobby
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("lobby.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/Publisher_material/Icon 50x50px.png"));
        stage.setTitle("Lobby");
        stage.setScene(scene);
        stage.setResizable(false);
        setUpBoardControllerTilesMap();
        stage.show();
        //set lobby as current stage
        StageController.setCurrentStage(stage);
    }

    public void setUpBoardControllerTilesMap() {
        HashMap<Color, HashMap<String, Integer>> tilesMap = new HashMap<>();
        for(Color color: Color.values()) {
            HashMap<String, Integer> temp = new HashMap<>();
            temp.put("@17_MyShelfie_BGA/item_tiles/"+ color + "/1.1.png", 3);
            temp.put("@17_MyShelfie_BGA/item_tiles/"+ color + "/1.2.png", 3);
            temp.put("@17_MyShelfie_BGA/item_tiles/"+ color + "/1.3.png", 3);
            tilesMap.put(color, temp);
        }

        BoardController.tilesMap = tilesMap;
    }

    public static void main(String[] args) {
        launch();
    }


}
