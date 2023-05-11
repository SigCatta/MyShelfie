package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.InsertTileMTS;
import it.polimi.ingsw.View.GUI.NodeData;
import it.polimi.ingsw.View.GUI.TilesSelectedCointainer;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShelfController {
    List<NodeData> tilesSelected = new ArrayList<>();
    NodeData currentTileSelected = null;
    int currentColumn;
    int currentRow;
    @FXML
    GridPane myShelf;

    @FXML
    ImageView personalGoalCard;

    @FXML
    Text score;

    @FXML
    TextField columnChosen;

    @FXML
    Button insertDoneButton;

    @FXML
    Button insertTileButton;

    @FXML
    ImageView itemTile1;

    @FXML
    ImageView itemTile2;

    @FXML
    ImageView itemTile3;

    @FXML
    ImageView wrongColumnImage;

    /**
     * this method saves the tiles picked up from the board in the local variable {@code tileselected}
     * the tiles picked up from the board that now need to be inserted in the player shelf
     */
    @FXML
    public void setUp() {
        tilesSelected = new ArrayList<>(TilesSelectedCointainer.getTilesSelected());
        List<ImageView> temp = List.of(itemTile1, itemTile2, itemTile3);

        for(int i = 0; i<tilesSelected.size(); i++) {
            setItemTileVisible(temp.get(i), tilesSelected.get(i).getUrl());
        }
    }

    static public void setItemTileVisible(ImageView itemTile, String path) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(path);
        itemTile.setVisible(true);
        itemTile.setImage(image);
    }

    public void setInsertDoneButtonVisible() {
        if(!itemTile1.isVisible() && !itemTile2.isVisible() && !itemTile3.isVisible())
            insertDoneButton.setVisible(true);
    }

    public void setScore(Text score) {
        this.score = score;
    }

    public void setPersonalGoalCard(String personalGoalCardPath) {
        this.personalGoalCard.setImage(new Image(personalGoalCardPath));
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
    public void onInsertDoneClicked() {
        //TODO notify model that the current player has terminated his turn
    }

    @FXML
    public void setCurrentTileSelected1() {
        currentTileSelected = tilesSelected.get(0);
        itemTile1.setEffect(new Glow(0.9));
        itemTile2.setEffect(new Glow(0));
        itemTile3.setEffect(new Glow(0));
    }

    @FXML
    public void setCurrentTileSelected2() {
        currentTileSelected = tilesSelected.get(1);
        itemTile2.setEffect(new Glow(0.9));
        itemTile1.setEffect(new Glow(0));
        itemTile3.setEffect(new Glow(0));
    }

    @FXML
    public void setCurrentTileSelected3() {
        currentTileSelected = tilesSelected.get(2);
        itemTile3.setEffect(new Glow(0.9));
        itemTile2.setEffect(new Glow(0));
        itemTile1.setEffect(new Glow(0));
    }

    @FXML
    public void onColumnInserted() {
        insertTileButton.setVisible(true);
        wrongColumnImage.setVisible(false);
    }

    @FXML
    public void onInsertTileClicked() {
        currentColumn = Integer.parseInt(columnChosen.getText());
        int index = tilesSelected.indexOf(currentTileSelected);
        int correctededIndex; //index to use in the InsertTileMTS

        if(index == 0) {
            correctededIndex = 0;
            itemTile1.setVisible(false);
        } else if(index == 1) {
            if(itemTile1.isVisible()) {
                correctededIndex = 1;
            } else {
                correctededIndex =0;
            }
            itemTile2.setVisible(false);
        } else {
            if(itemTile1.isVisible() && itemTile2.isVisible()) {
                correctededIndex = 2;
            } else if((!itemTile1.isVisible() && itemTile2.isVisible()) || (itemTile1.isVisible() && !itemTile2.isVisible())){
                correctededIndex =1;
            } else {
                correctededIndex = 0;
            }
            itemTile3.setVisible(false);
        }
        SocketClient.getInstance().sendCommand(new InsertTileMTS(correctededIndex, currentColumn));
    }

    public void tileCanBeInserted(boolean correct, int row) {
        if(correct) {
            currentRow = row;
            int index = tilesSelected.indexOf(currentTileSelected);

            if(index == 0) {
                itemTile1.setVisible(false);
            } else if(index == 1) {
                itemTile2.setVisible(false);
            } else {
                itemTile3.setVisible(false);
            }
            insertTile(currentTileSelected.getUrl(), new Point(currentRow, currentColumn));
        } else {
            wrongColumnImage.setVisible(true);
        }
    }

    public void insertTile(String path, Point position) {
        Image image = new Image(path);
        myShelf.add(new ImageView(image), position.y, position.x);   //add(object: elem, int: column, int: row)
        setInsertDoneButtonVisible();
    }
}
