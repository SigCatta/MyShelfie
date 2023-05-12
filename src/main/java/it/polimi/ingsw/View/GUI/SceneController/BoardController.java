package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.InsertTileMTS;
import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.GUI.NodeData;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ItemRefillUtility;
import it.polimi.ingsw.VirtualModel.*;
import it.polimi.ingsw.model.tiles.ItemTile;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardController implements VirtualModelObserver {

    int currentColumn;
    int currentRow;
    private static List<NodeData> chosenTiles = new ArrayList<>();
    private static NodeData currentTileSelected = null;

    public BoardController() {
        BoardRepresentation.getInstance().registerObserver(this);
        ShelvesRepresentation.getInstance().registerObserver(this);
    }


    @FXML
    GridPane board, myShelf, myChosenTilesTable;

    @FXML
    Button pickUpDoneButton;

    @FXML
    Button selectTileButton;

    @FXML
    ImageView itemTile1, itemTile2, itemTile3; //TODO remove

    @FXML
    ImageView errorImage;

    @FXML
    Text errorText;

    @FXML
    ImageView insertDoneImage;

    @FXML
    ImageView col0InsertButton, col1InsertButton, col2InsertButton, col3InsertButton, col4InsertButton;

    @FXML
    Text myNicknameText, player2Nickname, player3Nickname, player4Nickname;

    static GameState gameState;

    static String myNickname;


    /**
     * when the board in the virtual model updates this method is called
     * to refresh the board in the gui
     */
    public void updateBoard() {
        ItemTile[][] boardModel = BoardRepresentation.getInstance().getBoard();
        ItemRefillUtility.updateItemTileGrid(board, boardModel);
    }

    public void updateShelf() {
        ItemTile[][] shelfModel = ShelvesRepresentation.getInstance().getShelfMessage(SocketClient.getInstance().getNickname()).getShelf();
        ItemRefillUtility.updateItemTileGrid(myShelf, shelfModel);
    }

    public void updateChosenTilesTable() {
        List<ItemTile> chosenTilesTable = TilesTableRepresentation.getInstance().getTiles();
        ItemTile[][] chosenTilesMatrix = new ItemTile[1][chosenTilesTable.size()];
        ItemRefillUtility.updateItemTileGrid(myChosenTilesTable, chosenTilesMatrix);
    }


    /**
     * method called everyTime the state of the game changes
     *
     * @param gameState the current state of the game
     */
    public static void setGameState(GameState gameState) {
        gameState = gameState;
    }

    /**
     * method called everyTime activePlayer changes
     * @param nickname = nickname; the nickname of the active player
     */
    public static void setMyNickname(String nickname) {
        myNickname = nickname;
    }

    @FXML
    public void setNicknames() {
        //TODO set player nicknames text
    }

    public String getMyNickname() {
        return myNickname;
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
        for (NodeData node : chosenTiles) {
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
            for (NodeData node : chosenTiles) {
                node.getImageView().setVisible(true);
            }
            chosenTiles.clear();
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
        if (chosenTiles.size() < 3) {
            selectTileButton.setVisible(true);
            if (currentTileSelected != null) {
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

        if (chosenTiles.size() == 0) {
            setItemTile1Visible(currentTileSelected.getUrl());
        } else if (chosenTiles.size() == 1) {
            setItemTile2Visible(currentTileSelected.getUrl());
        } else if (chosenTiles.size() == 2) {
            setItemTile3Visible(currentTileSelected.getUrl());
        } else {
            selectTileButton.setVisible(false);
            return;
        }
        // set not visible the tile picked up in the board
        currentTileSelected.getImageView().setVisible(false);
        chosenTiles.add(currentTileSelected);
        currentTileSelected = null;

        if (chosenTiles.size() >= 3) selectTileButton.setVisible(false);
        insertDoneImage.setVisible(false);
    }

    /**
     * @param itemTile the tile to be placed on the board
     * @param position of the node in the board matrix where the tile needs to be inserted
     */
    public void placeTile(ItemTile itemTile, Point position) {

//        Integer imageNumber = tileToImage.get(itemTile.getId());
//
//        if(imageNumber == null){
//            imageNumber = tileToImage.put(itemTile.getId(), (int)(Math.random()*3));
//        }
//
//        String tilePath = ITEM_TILES_PACKAGE + "/" + itemTile.getColor() + "/" + imageNumber; //TODO filesystem problem with '/'
//
//        placeTile(tilePath, position);
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
        int index = chosenTiles.indexOf(currentTileSelected);
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
            int index = chosenTiles.indexOf(currentTileSelected);

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

        if (!itemTile1.isVisible() && !itemTile2.isVisible() && !itemTile3.isVisible()) {
            insertDoneImage.setVisible(true);
        }

    }

    @Override
    public void update() {
        updateBoard();
        //updateShelf();
        //updateChosenTilesTable(); //TODO it so that it doesn't do everything based on the turn
    }
}


