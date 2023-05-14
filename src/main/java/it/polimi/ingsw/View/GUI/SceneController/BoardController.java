package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.InsertTileMTS;
import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.GUI.NodeData;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ItemRefillUtility;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ItemTileMemory;
import it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers.*;
import it.polimi.ingsw.VirtualModel.*;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private static BoardController instance;

    private boolean initialized;

    private static List<NodeData> nodeData = new ArrayList<>();
    private static List<Image> chosenTilesImages = new ArrayList<>();
    private static NodeData currentTileSelected = null;
    /**
     * id of the tile to be sent to the shelf
     */
    private Integer selectedTileToSendToShelf;


    public BoardController() {

        instance = this;

        //boardActionListenerInit(); //initialize the action associated with the image click
    }

    public static BoardController getInstance() {
        return instance;
    }



    @FXML
    GridPane board, myShelf;
    @FXML
    FlowPane myChosenTilesTable;

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


    @FXML
    public void initScene() {
        if (initialized) return;
        setUpBoard();
        new BoardObserver().update();
        new PlayerObserver().update();
        new ShelfObserver().update();
        new TilesTableObserver().update();
        new GameObserver().update();
        initialized = true;
    }

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
        if (chosenTilesTable == null) return;
        Platform.runLater(() -> myChosenTilesTable.getChildren().clear());
        for (ItemTile itemTile : chosenTilesTable) {
            ImageView imageView = new ImageView(ItemTileMemory.getImage(itemTile.getId()));
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            imageView.setUserData(itemTile.getId());

            //avoids exception caused by running it instantly
            Platform.runLater(() -> myChosenTilesTable.getChildren().add(imageView));
        }
    }

    public void updateError() {
        errorImage.setVisible(true);
        System.out.println("There was an error: ");
        errorText.setVisible(true);
        errorText.setText(EchosRepresentation.getInstance().getMessage().getOutput());
        //TODO set a timer to make the error fade away
        Platform.runLater(() -> myChosenTilesTable.getChildren().clear());
        for (Node node : board.getChildren()) { //TODO optimize
            node.setVisible(true);
        }
    }

    public void updateGame() {
        gameState = GameRepresentation.getInstance().getGameState();
    }

    /**
     * methods called when the scene is initialized
     * to add to each one of the children nodes of the board matrix an event listener
     */
    private void setUpBoard() {
        for (Node node : board.getChildren()) {
            if (node == null) return;
            if (!(node instanceof ImageView)) return;

            attachBoardListener((ImageView) node);
        }
    }

    /**
     * action listener for every tile on the board
     * @param imageView object associated with the action
     */
    private void attachBoardListener(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {

            if (false) { //TODO JUST FOR TESTING (TO REMOVE)
                //TODO do not pick up if the tiles are not valid
                if (!gameState.equals(GameState.PICK_UP_TILES)) return;
                if (!getMyNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname())) return;
                if (myChosenTilesTable.getChildren().size() >= 3) return;
                if (myChosenTilesTable.getChildren().contains(imageView)) return; //TODO wrong
            }
            ImageView newImageView = new ImageView(imageView.getImage());
            newImageView.setFitHeight(70);
            newImageView.setFitWidth(70);
            newImageView.setUserData(imageView.getUserData()); //set the id of the tile

            myChosenTilesTable.getChildren().add(newImageView);
            imageView.setOpacity(0.15);
        });
    }


    @FXML
    public void onPickUpDoneClicked() {
        System.out.println("clicked the tick"); //TODO remove
        if (false) { //TODO remove after testing
            if (!GameRepresentation.getInstance().getGameState().equals(GameState.PICK_UP_TILES)) return;
            if (!getMyNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname())) return;
            if (myChosenTilesTable.getChildren().size() == 0) return;
        }

        ArrayList<Point> tilesPosition = new ArrayList<>();

        for (int i = 0; i < myChosenTilesTable.getChildren().size(); i++) {
            Node node = myChosenTilesTable.getChildren().get(i);

            if (node == null) return;
            if (!(node instanceof ImageView)) return;
            ImageView imageView = (ImageView) node;

            int id = (int) imageView.getUserData();

            tilesPosition.add(ItemTileMemory.getPoint(id));
        }
        System.out.println("sending the tiles!"); //TODO remove
        SocketClient.getInstance().sendCommand(new PickUpTilesMTS(tilesPosition));
    }

    @FXML
    public void setUpChosenTilesTable() {
        if (false) {
            if (!(GameRepresentation.getInstance().getGameState().equals(GameState.INSERT_TILES))) return;
        }

        for (Node node : myChosenTilesTable.getChildren()) {
            if (node == null) return;
            if (!(node instanceof ImageView)) return;

            attachChosenTileListener((ImageView) node);
        }
    }

    private void attachChosenTileListener(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {

            if (false) { //TODO JUST FOR TESTING (TO REMOVE)
                if (!gameState.equals(GameState.INSERT_TILES)) return;
                if (!getMyNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname())) return;
                if (selectedTileToSendToShelf != null) return;
            }

            selectedTileToSendToShelf = (Integer) imageView.getUserData();
            System.out.println("the selected tile has id: " + selectedTileToSendToShelf); //TODO remove
        });
    }

    @FXML
    public void onCol0InserterSelected() {
        onInsertTileClicked(0);
    }

    @FXML
    public void onCol1InserterSelected() {
        onInsertTileClicked(1);
    }

    @FXML
    public void onCol2InserterSelected() {
        onInsertTileClicked(2);
    }

    @FXML
    public void onCol3InserterSelected() {
        onInsertTileClicked(3);
    }

    @FXML
    public void onCol4InserterSelected() {
        onInsertTileClicked(4);
    }

    @FXML
    public void onInsertTileClicked(int column) {
        if (false) { //TODO remove
            if (!gameState.equals(GameState.INSERT_TILES)) return;
            if (!myNickname.equals(GameRepresentation.getInstance().getActivePlayerNickname())) return;
        }

        ItemTile tileToSend = ItemTileMemory.getTile(selectedTileToSendToShelf);

        int indexInTheTable = -1;

        List<ItemTile> table = TilesTableRepresentation.getInstance().getTiles();
        for (int i = 0; i < table.size(); i++) { //TODO optimize
            if (table.get(i).getId() == tileToSend.getId()) {
                indexInTheTable = i;
                break;
            }
        }
        System.out.println("the index sent was: " + indexInTheTable); //TODO remove

        SocketClient.getInstance().sendCommand(new InsertTileMTS(indexInTheTable, column));
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


    public void onTileSelected(ImageView nodeSelected, int row, int column) {
        Color tileColor = null;
        String tileUrl = nodeSelected.getImage().getUrl();
        for(Color color: Color.values()) {
            if(tileUrl.contains(String.valueOf(color))) {
                tileColor = color;
            }
        }
        if (nodeData.size() < 3) {
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

}


