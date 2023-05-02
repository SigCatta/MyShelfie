package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.model.tiles.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardController {
    /**
     * map containing as keys the color of the item tile (es. Color.BLUE) and
     * as values the hashmap that contains as key the path of the specific tiles (es. it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/item_tiles/Cornici1.1.png)
     * and as values the nuber of tiles of that specific type left
     */
    HashMap<Color, HashMap<String, Integer>> tilesMap;

    List<Color> tilesSelected = new ArrayList<>();

    @FXML
    GridPane matrix;

    @FXML
    ImageView commonGoalCard1;

    @FXML
    ImageView commonGoalCard2;

    @FXML
    Button pickUpDoneButton;

    @FXML
    Button selectTileButton;

    @FXML
    ImageView itemTile1;

    @FXML
    ImageView itemTile2;

    @FXML
    ImageView itemTile3;

    List<Boolean> selectedItemTiles = new ArrayList<>();

    public void setItemTile1Visible(String path) {
        Image image = new Image(path);
        itemTile1.setVisible(true);
        itemTile1.setImage(image);
        itemTileSelectedHelper();
    }
    public void setItemTile2Visible(String path) {
        Image image = new Image(path);
        itemTile2.setVisible(true);
        itemTile2.setImage(image);
        itemTileSelectedHelper();
    }
    public void setItemTile3Visible(String path) {
        Image image = new Image(path);
        itemTile3.setVisible(true);
        itemTile3.setImage(image);
        itemTileSelectedHelper();
    }

    public void itemTileSelectedHelper() {
        setPickUpDoneButtonVisible();
        selectedItemTiles.add(true);
    }

    public void setCommonGoalCard1(String commonGoalCard1Path) {
        this.commonGoalCard1.setImage(new Image(commonGoalCard1Path));
    }

    public void setCommonGoalCard2(String commonGoalCard2Path) {
        this.commonGoalCard2.setImage(new Image(commonGoalCard2Path));
    }

    public void setPickUpDoneButtonVisible() {
        pickUpDoneButton.setVisible(true);
    }

    @FXML
    public void onShowMyShelfClicked() {
        StageController.changeScene("shelf.fxml", "My shelf");
    }

    @FXML
    public void onShowOtherShelvesClicked() {
        StageController.changeScene("other_shelves.fxml", "Other shelves");
    }

    @FXML
    public void onGoToChatClicked() {
        StageController.changeScene("chat.fxml", "Chat");
    }

    @FXML
    public void onPickUpDoneClicked() {
        //TODO: send tiles to virtual model


        //go to player shelf
        onShowMyShelfClicked();
    }

    @FXML
    public void setUpBoard() {
        for(Node node: matrix.getChildren()) {
            node.setOnMouseClicked(buttonEventHandler());
        }
    }

    EventHandler<MouseEvent> buttonEventHandler(){
        return event -> {
            ImageView nodeSelected = (ImageView) event.getTarget();
            int row = GridPane.getRowIndex(nodeSelected);
            int column = GridPane.getColumnIndex(nodeSelected);
            onTileSelected(nodeSelected, row, column);
        };
    }


    public void onTileSelected(ImageView nodeSelected, int row, int column) {
        Color tileColor;
        String tileUrl = nodeSelected.getImage().getUrl();
        for(Color color: Color.values()) {
            if(tileUrl.contains(String.valueOf(color))) {
                tileColor = color;
            }
        }
        if(true)   { //TODO: check if tile can be picked up
            nodeSelected.setVisible(false);
            if (selectedItemTiles.size() == 0) {
                setItemTile1Visible(tileUrl);
            } else if (selectedItemTiles.size() == 1) {
                setItemTile2Visible(tileUrl);
            } else {
                setItemTile3Visible(tileUrl);
            }
        }
    }

    public void onPickUpSelectedTileClicked() {
        if(tilesSelected.size() > 0) setPickUpDoneButtonVisible();
        if(tilesSelected.size() >= 3) selectTileButton.setVisible(false);

        //TODO: move tile from board to array

    }

    public void placeTile(Color color, Point position) {
        HashMap<String, Integer> specificTilesMap = tilesMap.get(color);
        String tilePath = null;

        while (tilePath == null) {
            int index=(int)(Math.random()*specificTilesMap.size());
            int count = 0;
            for(Map.Entry<String, Integer> entry : specificTilesMap.entrySet()) {
                if(index >= count && entry.getValue()>0) {
                    tilePath = entry.getKey();
                    break;
                }
                count++;
            }
        }
        specificTilesMap.put(tilePath, specificTilesMap.get(tilePath)-1);
        tilesMap.put(color, specificTilesMap);
        placeTile(tilePath, position);
    }

    public void placeTile(String path, Point position) {
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        matrix.add(new ImageView(image), position.y, position.x);   //add(object: elem, int: column, int: row)
    }
}


