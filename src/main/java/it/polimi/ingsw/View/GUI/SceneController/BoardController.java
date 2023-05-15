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

    private boolean initialized, alreadySetUpTable;
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
    Text myNicknameText, player2Nickname, player3Nickname, player4Nickname;


    @FXML
    public void initScene() {
        if (initialized) return;
        setUpBoard();
        ShelfMemory.reset();
        initInsertButtons();
        new BoardObserver().update();
        new PlayerObserver().update();
        new ShelfObserver().update();
        new TilesTableObserver().update();
        new GameObserver().update();
        new ErrorObserver();
        initialized = true;
    }

    private void initInsertButtons() {
        col0InsertButton.setOnMouseEntered(mouseEvent -> col0InsertButton.getStyleClass().add("edge-effect"));
        col0InsertButton.setOnMouseExited(mouseEvent -> col0InsertButton.getStyleClass().remove("edge-effect"));

        col1InsertButton.setOnMouseEntered(mouseEvent -> col1InsertButton.getStyleClass().add("edge-effect"));
        col1InsertButton.setOnMouseExited(mouseEvent -> col1InsertButton.getStyleClass().remove("edge-effect"));

        col2InsertButton.setOnMouseEntered(mouseEvent -> col2InsertButton.getStyleClass().add("edge-effect"));
        col2InsertButton.setOnMouseExited(mouseEvent -> col2InsertButton.getStyleClass().remove("edge-effect"));

        col3InsertButton.setOnMouseEntered(mouseEvent -> col3InsertButton.getStyleClass().add("edge-effect"));
        col3InsertButton.setOnMouseExited(mouseEvent -> col3InsertButton.getStyleClass().remove("edge-effect"));

        col4InsertButton.setOnMouseEntered(mouseEvent -> col4InsertButton.getStyleClass().add("edge-effect"));
        col4InsertButton.setOnMouseExited(mouseEvent -> col4InsertButton.getStyleClass().remove("edge-effect"));
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

        System.out.println("There was an error: ");
        errorText.setText(EchosRepresentation.getInstance().peekMessage().getOutput());
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), errorPane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }

    public void updateGame() {

    }

    /**
     * methods called when the scene is initialized
     * to add to each one of the children nodes of the board matrix an event listener
     */
    private void setUpBoard() {
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

        Point position = ItemTileMemory.getPoint(selectedTileToSendToShelf);
        BoardMemory.get(position.x, position.y).getStyleClass().clear();
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

        for (Node imageView : myChosenTilesTable.getChildren()) {
            if (!(imageView instanceof ImageView)) return;
            imageView.getStyleClass().remove("edge-effect");
        }
        selectedTileToSendToShelf = -1;
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


