package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.InsertTileMTS;
import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.GUI.NodeData;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    int currentColumn;
    int currentRow;
    static List<NodeData> tilesSelected = new ArrayList<>();
    static NodeData currentTileSelected = null;

    @FXML
    GridPane board;

    @FXML
    GridPane myShelf;

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

    @FXML
    ImageView errorImage;

    @FXML
    Text errorText;

    @FXML
    ImageView insertDoneImage;

    @FXML
    ImageView col0InsertButton;

    @FXML
    ImageView col1InsertButton;

    @FXML
    ImageView col2InsertButton;

    @FXML
    ImageView col3InsertButton;

    @FXML
    ImageView col4InsertButton;

    @FXML
    Text myNicknameText;

    @FXML
    Text player2Nickname;

    @FXML
    Text player3Nickname;

    @FXML
    Text player4Nickname;

    static GameState gameState;

    @FXML
    public void setNicknames() {
        myNicknameText.setText(SocketClient.getInstance().getNickname());

        List<Text> nicknamesTextsList = List.of(player2Nickname, player3Nickname, player4Nickname);
        List<String> nicknames = PlayersRepresentation.getInstance().getPlayersList();
        int j=0;

        for(int i=0; i<nicknames.size(); i++) {
            if(!nicknames.get(i).equals(myNicknameText.getText())){
                nicknamesTextsList.get(j).setText(nicknames.get(i));
                j++;
            }
        }
    }

    public String getMyNickname() {
        return myNicknameText.getText();
    }

    public void setItemTile1Visible(String path) {
        Image image = new Image(path);
        itemTile1.setImage(image);
        itemTile1.setVisible(true);
    }
    public void setItemTile2Visible(String path) {
        Image image = new Image(path);
        itemTile2.setImage(image);
        itemTile2.setVisible(true);
    }
    public void setItemTile3Visible(String path) {
        Image image = new Image(path);
        itemTile3.setImage(image);
        itemTile3.setVisible(true);
    }

    @FXML
    public void onObjectivesClicked() {
        StageController.changeScene("fxml/objectives_card_scene.fxml", "Objective cards scene");
    }

    @FXML
    public void onShelvesClicked() {
        StageController.changeScene("fxml/other_player_shelves.fxml", "Other players shelves");
    }

    @FXML
    public void onChatClicked() {
        StageController.changeScene("fxml/chat_scene.fxml", "Chat");
    }

    @FXML
    public void onPickUpDoneClicked() {
        if (!gameState.equals(GameState.PICK_UP_TILES) || !getMyNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname())) return;

        ArrayList<Point> tilesPosition = new ArrayList<>();
        for(NodeData node: tilesSelected) {
            tilesPosition.add(node.getPosition());
        }
        SocketClient.getInstance().sendCommand(new PickUpTilesMTS(tilesPosition));
    }

    public void canTilesBePickedUp(boolean correctTiles) {
        //check if tiles can be picked up
        if(correctTiles) {
            setColInserterButtonVisible(true);
            hideErrorMessage();
        }
        else {
            setColInserterButtonVisible(false);
            showErrorMessage("Tiles can't be picked up!");
            for(NodeData node: tilesSelected) {
                node.getImageView().setVisible(true);
            }
            tilesSelected.clear();
            itemTile1.setVisible(false);
            itemTile2.setVisible(false);
            itemTile3.setVisible(false);
        }
    }

    @FXML
    public void onCol0InserterSelected() {onInsertTileClicked(0);}

    @FXML
    public void onCol1InserterSelected() {onInsertTileClicked(1);}

    @FXML
    public void onCol2InserterSelected() {onInsertTileClicked(2);}

    @FXML
    public void onCol3InserterSelected() {onInsertTileClicked(3);}

    @FXML
    public void onCol4InserterSelected() {onInsertTileClicked(4);}

    private void setColInserterButtonVisible(boolean correct) {
        col0InsertButton.setVisible(correct);
        col1InsertButton.setVisible(correct);
        col2InsertButton.setVisible(correct);
        col3InsertButton.setVisible(correct);
        col4InsertButton.setVisible(correct);
    }

    private void showErrorMessage(String message) {
        errorText.setText(message);
        errorImage.setVisible(true);
    }

    private void hideErrorMessage() {
        errorImage.setVisible(false);
        errorText.setVisible(false);
    }

    /**
     * methods called when mouse enters the board
     * it adds to each one of the children nodes of the board matrix an event listener
     */
    @FXML
    public void setUpBoard() {
        for(Node node: board.getChildren()) {
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
            currentTileSelected.setImageView(nodeSelected);
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

        if(tilesSelected.size() >= 3) selectTileButton.setVisible(false);
        insertDoneImage.setVisible(false);
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
        board.add(new ImageView(image), position.y, position.x);   //add(object: elem, int: column, int: row)
    }

    @FXML
    public void onInsertTileClicked(int column) {
        if (!gameState.equals(GameState.INSERT_TILES) || !getMyNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname())) return;

        currentColumn = column;
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
            hideErrorMessage();
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
            showErrorMessage("Wrong column selected!");
        }
    }

    public void insertTile(String path, Point position) {
        Image image = new Image(path);
        myShelf.add(new ImageView(image), position.y, position.x);   //add(object: elem, int: column, int: row)

        if(!itemTile1.isVisible() && !itemTile2.isVisible() && !itemTile3.isVisible()) {
            insertDoneImage.setVisible(true);
        }

    }
}


