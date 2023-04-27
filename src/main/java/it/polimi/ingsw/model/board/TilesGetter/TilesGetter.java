package it.polimi.ingsw.model.board.TilesGetter;

import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.InsertTilesState;
import it.polimi.ingsw.model.GameState.PickUpTilesState;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TilesGetter {
    private Board board;
    private final PickUpValidator PICK_UP_VALIDATOR;
    private Player activePlayer;
    private Integer chosenColumn;
    private TilesToShelfSender tilesToShelfSender;
    private Game game;
    /**
     * The list of ItemTiles to be inserted.
     */
    private ChosenTilesTable chosenTilesTable;
    /**
     * every item in the list is linked to the ItemTile in the tilesToBeInserted list,
     * and it is true if it has already been sent to the shelf, false otherwise
     */
    private List<Boolean> positionsAlreadySelected;

    public TilesGetter(Game game){
        this.game = game;
        chosenTilesTable = new ChosenTilesTable();
        tilesToShelfSender = new TilesToShelfSender(game, chosenTilesTable);
        PICK_UP_VALIDATOR = new PickUpValidator(game);
        board = game.getBoard();
        positionsAlreadySelected = new ArrayList<>();
    }

    /**
     * @param chosenPositions The list of cells on the board chosen by the player
     * @return true if the positions are valid and there are enough free cell left in the player's shelf
     */
    public boolean pickUpTiles(ArrayList<Point> chosenPositions){

        this.positionsAlreadySelected = new ArrayList<>();
        this.chosenColumn = null;

        if(!PICK_UP_VALIDATOR.isValid(chosenPositions)) {
            game.getVirtualView().send(new ErrorMessage("You can't pick up these tiles"));
            return false;
        }

        if(tooManyTilesChosen(chosenPositions.size()))  {
            game.getVirtualView().send(new ErrorMessage("Choose less tiles"));
            return false;
        }

        //send tiles to the ChosenTilesTable and remove them from the board
        for(Point position : chosenPositions) {
            chosenTilesTable.insertTile(board.removeItemTile(position));
        }

        game.setGameState(new InsertTilesState());

        return true;
    }

    /**
     * @return true if there isn't any columns with enough free cells to contain all the new tiles
     */
    private boolean tooManyTilesChosen(int size) {
        activePlayer = game.getActivePlayer();

        if(size > game.getMAX_TILES_FROM_BOARD())  return true;

        Shelf shelf = activePlayer.getShelf();
        for (int i = 0; i < shelf.getCOLUMNS(); i++) {
            if(shelf.getNumOfBoxLeftInCol(i) >= size) return false;    //there is still enough free cell in at least a column
        }
        return true;    //not enough free cell in any columns
    }

    public ChosenTilesTable getChosenTilesTable() {
        return chosenTilesTable;
    }

    public List<Boolean> getPositionsAlreadySelected() {
        return positionsAlreadySelected;
    }

    public boolean sendTilesToShelf(int tileIndex, int column){
        return tilesToShelfSender.sendTilesToShelf(tileIndex, column);
    }

}
