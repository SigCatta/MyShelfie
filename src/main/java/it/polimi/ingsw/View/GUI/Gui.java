package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.VirtualView.Messages.BoardMTC;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("fxml/board.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/Publisher_material/Icon 50x50px.png"));
        stage.setTitle("Lobby");

        stage.setMaximized(true);

        stage.setScene(scene);
        stage.setResizable(true);
        setUpBoardControllerTilesMap();
        stage.show();
        //set lobby as current stage
        StageController.setCurrentStage(stage);

        ItemTile[][] matrix1 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        Board board = new Board(matrix1);
        BoardRepresentation.getInstance().setBoard(new BoardMTC(board));

    }

    /**
     * method that fills the map saved in BoardController that is used to calculate which particular tile of a given color
     * can be inserted in the board
     */
    public void setUpBoardControllerTilesMap() {
        HashMap<Color, HashMap<String, Integer>> tilesMap = new HashMap<>();
        for(Color color: Color.values()) {
            HashMap<String, Integer> temp = new HashMap<>();
            temp.put("@17_MyShelfie_BGA/item_tiles/"+ color + "/1.1.png", 3);
            temp.put("@17_MyShelfie_BGA/item_tiles/"+ color + "/1.2.png", 3);
            temp.put("@17_MyShelfie_BGA/item_tiles/"+ color + "/1.3.png", 3);
            tilesMap.put(color, temp);
        }

        //BoardController.tilesMap = tilesMap;
    }

    public static void main(String[] args) {
        launch();
    }


}
