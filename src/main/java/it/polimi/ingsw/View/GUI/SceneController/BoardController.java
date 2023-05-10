package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.View.GUI.NodeData;
import it.polimi.ingsw.View.GUI.TilesSelectedCointainer;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardController {
    /**
     * map containing as keys the color of the item tile (es. Color.BLUE) and
     * as values the hashmap that contains as key the path of the specific tiles (es. it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/item_tiles/1.1.png)
     * and as values the number of tiles of that specific type left
     */
    static public HashMap<Color, HashMap<String, Integer>> tilesMap;

    static List<NodeData> tilesSelected = new ArrayList<>();
    static NodeData currentTileSelected = null;

    @FXML
    GridPane matrix;

    @FXML
    ImageView commonGoalCard1;

    @FXML
    ImageView commonGoalCard2;

    @FXML
    Button pickUpDoneButton;

    @FXML
    Button continueButton;

    @FXML
    Button selectTileButton;

    @FXML
    ImageView itemTile1;

    @FXML
    ImageView wrongTilesSelected;

    @FXML
    ImageView itemTile2;

    @FXML
    ImageView itemTile3;

    @FXML
    Text pointCG1Text;

    @FXML
    Text pointCG2Text;

    @FXML
    TextField cgDescription1;

    @FXML
    TextField cgDescription2;

    public void setPointCG1Text(int point) {
        pointCG1Text.setText(String.valueOf(point));
    }

    public void setPointCG2Text(int point) {
        pointCG2Text.setText(String.valueOf(point));
    }

    public void setItemTile1Visible(String path) {
        Image image = new Image(path);
        itemTile1.setImage(image);
        itemTile1.setVisible(true);
        setPickUpDoneButtonVisible();
    }
    public void setItemTile2Visible(String path) {
        Image image = new Image(path);
        itemTile2.setImage(image);
        itemTile2.setVisible(true);
        setPickUpDoneButtonVisible();
    }
    public void setItemTile3Visible(String path) {
        Image image = new Image(path);
        itemTile3.setImage(image);
        itemTile3.setVisible(true);
        setPickUpDoneButtonVisible();
    }

    public void setCommonGoalCard1(String commonGoalCard1Path) {
        this.commonGoalCard1.setImage(new Image(commonGoalCard1Path));
    }

    public void setCommonGoalCard2(String commonGoalCard2Path) {
        this.commonGoalCard2.setImage(new Image(commonGoalCard2Path));
    }

    @FXML
    public void onCommonGoal1Clicked() {
        cgDescription1.setText("Description");  //TODO
        cgDescription1.setVisible(true);
        commonGoalCard1.setVisible(false);
    }

    @FXML
    public void onCommonGoal2Clicked() {
        cgDescription2.setText("Description");  //TODO
        cgDescription2.setVisible(true);
        commonGoalCard2.setVisible(false);
    }

    @FXML
    public void cgDescription1Clicked() {
        cgDescription1.setVisible(false);
        commonGoalCard1.setVisible(true);
    }

    @FXML
    public void cgDescription2Clicked() {
        cgDescription1.setVisible(false);
        commonGoalCard1.setVisible(true);
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
        StageController.changeScene("chat_scrollable.fxml", "Chat");
        //StageController.changeScene("chat.fxml", "Chat");
    }

    @FXML
    public void onPickUpDoneClicked() {
        ArrayList<Point> tilesPosition = new ArrayList<>();
        for(NodeData node: tilesSelected) {
            tilesPosition.add(node.getPosition());
        }
        SocketClient.getInstance().sendCommand(new PickUpTilesMTS(tilesPosition));
    }

    public void canTilesBePickedUp(boolean correctTiles) {
        //check if tiles can be picked up
        if(correctTiles) {
            //tiles can be picked up
            continueButton.setVisible(true);
        } else {
            continueButton.setVisible(false);
            for(NodeData node: tilesSelected) {
                node.getImageView().setVisible(true);
            }
        }
        tilesSelected.clear();
        itemTile1.setVisible(false);
        itemTile2.setVisible(false);
        itemTile3.setVisible(false);
    }

    @FXML
    public void onContinueButtonClicked() {
        //go to player shelf
        TilesSelectedCointainer.setTilesSelected(tilesSelected);
        onShowMyShelfClicked();
    }

    /**
     * methods called when mouse enters the board
     * it adds to each one of the children nodes of the board matrix an event listener
     */
    @FXML
    public void setUpBoard() {
        for(Node node: matrix.getChildren()) {
            node.setOnMouseClicked(nodeEventHandler());
        }
    }

    /**
     *
     * @return an event fired when a child node of the board matrix is selected
     */
    EventHandler<MouseEvent> nodeEventHandler(){
        return event -> {
            ImageView nodeSelected = (ImageView) event.getTarget();
            int row = GridPane.getRowIndex(nodeSelected);
            int column = GridPane.getColumnIndex(nodeSelected);
            onTileSelected(nodeSelected, row, column);
        };
    }

    public void onTileSelected(ImageView nodeSelected, int row, int column) {
        Color tileColor = null;
        String tileUrl = nodeSelected.getImage().getUrl();
        for(Color color: Color.values()) {
            if(tileUrl.contains(String.valueOf(color))) {
                tileColor = color;
            }
        }
        if(tilesSelected.size()<3 )   {
            selectTileButton.setVisible(true);
            if(currentTileSelected!=null) {
                currentTileSelected.getImageView().setEffect(new Glow(0));
            }
            nodeSelected.setEffect(new Glow(0.9));
            currentTileSelected = new NodeData(tileUrl, tileColor, nodeSelected, new Point(row, column));
        } else {
            selectTileButton.setVisible(false);
        }
    }

    @FXML
    public void onPickUpSelectedTileClicked() {
        if(currentTileSelected == null) return;

        if (tilesSelected.size() == 0) {
            setItemTile1Visible(currentTileSelected.getUrl());
        } else if (tilesSelected.size() == 1) {
            setItemTile2Visible(currentTileSelected.getUrl());
        } else if(tilesSelected.size() == 2){
            setItemTile3Visible(currentTileSelected.getUrl());
        } else {
            selectTileButton.setVisible(false);
            return;
        }
        // set not visible the tile picked up in the board
        currentTileSelected.getImageView().setVisible(false);
        tilesSelected.add(currentTileSelected);
        currentTileSelected = null;

        setPickUpDoneButtonVisible();
        if(tilesSelected.size() >= 3) selectTileButton.setVisible(false);
    }

    /**
     *
     * @param color the color of the tile to be placed on the board
     * @param position of the node in the board matrix where the tile needs to be inserted
     */
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
        imageView.setVisible(true);
        matrix.add(new ImageView(image), position.y, position.x);   //add(object: elem, int: column, int: row)
    }
}


