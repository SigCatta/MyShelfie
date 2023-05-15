package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Controller.Client.InsertTileMTS;
import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.GUI.SceneController.Utility.BoardMemory;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ItemRefillUtility;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ItemTileMemory;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ShelfMemory;
import it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers.*;
import it.polimi.ingsw.VirtualModel.*;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private static BoardController instance;

    private boolean initialized;
    private List<Integer> cardsSelectedFromBoard = new ArrayList<>();

    /**
     * id of the tile to be sent to the shelf
     */
    private int selectedTileToSendToShelf = -1;


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
    AnchorPane chooseColumnPane;

    @FXML
    Button selectTileButton;

    @FXML
    ImageView itemTile1, itemTile2, itemTile3; //TODO remove


    @FXML
    AnchorPane errorPane;
    @FXML
    ImageView errorImage;
    @FXML
    Text errorText;


    @FXML
    ImageView col0InsertButton, col1InsertButton, col2InsertButton, col3InsertButton, col4InsertButton;

    @FXML
    FlowPane playersPane;

    @FXML
    public void initScene() {
        if (initialized) return;
        initBoard();
        initInsertButtons();
        initPlayersName();
        initShelf();

        new BoardObserver().update();
        new PlayerObserver().update();
        new ShelfObserver().update();
        new TilesTableObserver().update();
        new GameObserver().update();
        new ErrorObserver();
        initialized = true;
    }

    private void initInsertButtons() {
        for (Node node : chooseColumnPane.getChildren()) {
            node.setOnMouseEntered(mouseEvent -> node.getStyleClass().add("edge-effect"));
            node.setOnMouseExited(mouseEvent -> node.getStyleClass().remove("edge-effect"));
        }
    }

    private void initPlayersName() {
        List<String> nicknames = PlayersRepresentation.getInstance().getPlayersList();
        for (String nickname : nicknames) {
            Text playerName = new Text(nickname);
            playerName.getStyleClass().add("nickname"); //TODO change in something cooler
            playersPane.getChildren().add(playerName);
        }
        updateChangeTurn();
    }

    private void initShelf() {
        for (int row = 0; row < myShelf.getRowCount(); row++) {
            for (int col = 0; col < myShelf.getColumnCount(); col++) {
                if (ShelfMemory.get(row, col) == null) {
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(45);
                    imageView.setFitWidth(45);
                    ShelfMemory.put(imageView, row, col);
                }
                myShelf.add(ShelfMemory.get(row, col), col, row);
            }
        }
    }

    /**
     * when the board in the virtual model updates this method is called
     * to refresh the board in the gui
     */
    public void updateBoard() {
        ItemTile[][] boardModel = BoardRepresentation.getInstance().getBoard();
        ItemRefillUtility.updateBoardGrid(boardModel);
    }

    public void updateShelf() {
        ItemTile[][] shelfModel = ShelvesRepresentation.getInstance().getShelfMessage(SocketClient.getInstance().getNickname()).getShelf();
        System.out.println("Updating the shelf...");//TODO remove
        ItemRefillUtility.updateShelfGrid(myShelf, shelfModel);
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
        errorText.setVisible(true);
        errorText.setWrappingWidth(300);

        System.out.println("There was an error: "); //TODO remove
        errorText.setText(EchosRepresentation.getInstance().peekMessage().getOutput());
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), errorPane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();

        EchosRepresentation.getInstance().clean();
    }

    public void updateGame() {

    }

    public void updateChangeTurn() {
        for (Node text : playersPane.getChildren()) {
            if (!(text instanceof Text)) return;
            text.getStyleClass().remove("fancy-text");
            if (((Text) text).getText().equals(GameRepresentation.getInstance().getActivePlayerNickname())) {
                text.getStyleClass().add("fancy-text");
            }
        }
    }

    /**
     * methods called when the scene is initialized
     * to add to each one of the children nodes of the board matrix an event listener
     */
    private void initBoard() {
        for (Node node : board.getChildren()) {
            if (node == null) return;
            if (!(node instanceof ImageView)) return;

            Integer c = GridPane.getColumnIndex(node);
            Integer r = GridPane.getRowIndex(node);
            if (c == null || r == null) continue;
            BoardMemory.put((ImageView) node, r, c);

            attachBoardListener((ImageView) node);
        }
    }


    /**
     * action listener for every tile on the board
     * @param imageView object associated with the action
     */
    private void attachBoardListener(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {

            if (imageView.getImage() == null) return;
            if (!GameRepresentation.getInstance().getGameState().equals(GameState.PICK_UP_TILES)) return;
            //TODO do not pick up if the tiles are not valid
            if (!SocketClient.getInstance().getNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname())) {
                return;
            }

            if (cardsSelectedFromBoard.contains((Integer) imageView.getUserData())) {
                cardsSelectedFromBoard.remove((Integer) imageView.getUserData());
                imageView.getStyleClass().clear();
                return;
            }
            if (cardsSelectedFromBoard.contains((Integer) imageView.getUserData())) return;
            if (cardsSelectedFromBoard.size() >= 3) return;

            imageView.getStyleClass().add("edge-effect2");
            cardsSelectedFromBoard.add((Integer) imageView.getUserData());

        });

        imageView.setOnMouseEntered(mouseEvent -> imageView.getStyleClass().add("edge-effect"));

        imageView.setOnMouseExited(mouseEvent -> imageView.getStyleClass().remove("edge-effect"));
    }


    @FXML
    public void onPickUpDoneClicked() {
        System.out.println("clicked the tick"); //TODO remove

        if (cardsSelectedFromBoard.size() == 0) return;
        if (!GameRepresentation.getInstance().getGameState().equals(GameState.PICK_UP_TILES)) return;
        if (!SocketClient.getInstance().getNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname()))
            return;

        ArrayList<Point> tilesPosition = new ArrayList<>();

        for (Integer id : cardsSelectedFromBoard) {
            Point position = ItemTileMemory.getPoint(id);
            tilesPosition.add(position);
            BoardMemory.get(position.x, position.y).getStyleClass().clear();
        }

        cardsSelectedFromBoard = new ArrayList<>();

        System.out.println("sending the tiles!"); //TODO remove
        SocketClient.getInstance().sendCommand(new PickUpTilesMTS(tilesPosition));
    }

    @FXML
    public void setUpChosenTilesTable() {

        if (!(GameRepresentation.getInstance().getGameState().equals(GameState.INSERT_TILES))) return;

        for (Node node : myChosenTilesTable.getChildren()) {
            if (node == null) return;
            if (!(node instanceof ImageView)) return;

            attachChosenTileListener((ImageView) node);
        }
    }

    private void attachChosenTileListener(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {

            if (!GameRepresentation.getInstance().getGameState().equals(GameState.INSERT_TILES)) return;
            if (!SocketClient.getInstance().getNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname()))
                return;

            //if the user is selecting the same tile it means he wants to remove it
            if (selectedTileToSendToShelf == (int) imageView.getUserData()) {
                selectedTileToSendToShelf = -1;
                imageView.getStyleClass().remove("edge-effect2");
                return;
            }

            if (selectedTileToSendToShelf >= 0) return; //if a tile was already chosen

            selectedTileToSendToShelf = (int) imageView.getUserData();
            imageView.getStyleClass().add("edge-effect2");

            System.out.println("the selected tile has id: " + selectedTileToSendToShelf); //TODO remove
        });

        imageView.setOnMouseEntered(mouseEvent -> imageView.getStyleClass().add("edge-effect"));

        imageView.setOnMouseExited(mouseEvent -> imageView.getStyleClass().remove("edge-effect"));

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
    public synchronized void onInsertTileClicked(int column) {

        if (!GameRepresentation.getInstance().getGameState().equals(GameState.INSERT_TILES)) return;
        if (!SocketClient.getInstance().getNickname().equals(GameRepresentation.getInstance().getActivePlayerNickname()))
            return;
        if (selectedTileToSendToShelf < 0) return;

        ItemTile tileToSend = ItemTileMemory.getTile(selectedTileToSendToShelf);

        myChosenTilesTable.getChildren().forEach(node -> node.getStyleClass().clear());
        selectedTileToSendToShelf = -1;

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

        myChosenTilesTable.getChildren().forEach(image -> image.getStyleClass().clear());
    }


    @FXML
    public void onObjectivesClicked() {
        StageController.changeScene("fxml/objective_cards_scene.fxml", "Objective cards scene");
    }

    @FXML
    public void onShelvesClicked() {
        StageController.changeScene("fxml/other_player_shelves.fxml", "Other players shelves");
    }

    @FXML
    public void onChatClicked() {
        StageController.changeScene("fxml/chat_scene.fxml", "Chat");
    }


}


