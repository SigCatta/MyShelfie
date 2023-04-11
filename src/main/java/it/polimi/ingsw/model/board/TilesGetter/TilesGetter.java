package it.polimi.ingsw.model.board.TilesGetter;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
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
    private boolean readyToInsert;
    private int chosenColumn;
    private Game game;
    /**
     * The list of ItemTiles to be inserted.
     */
    private List<ItemTile> tilesToBeInserted ;

    public TilesGetter(Game game){
        this.game = game;
        PICK_UP_VALIDATOR = new PickUpValidator(game);
        board = game.getBoard();
        tilesToBeInserted = new ArrayList<>();
        readyToInsert = false;
    }

    public void setActivePlayer(Player activePlayer) {
        readyToInsert = false;
        this.activePlayer = activePlayer;
    }

    /**
     * @param chosenPositions The list of cells on the board chosen by the player
     * @return true if the positions are valid and there are enough free cell left in the player's shelf
     */
    public boolean pickUpTiles(ArrayList<Point> chosenPositions){
        if(!PICK_UP_VALIDATOR.isValid(chosenPositions)) {
            //TODO tell the user to choose
            return false;
        }

        if(tooManyTilesChosen(chosenPositions.size()))  {
            //TODO tell the user to choose less tiles
            return false;
        }

        //send tiles to the shelf buffer and remove them from the board
        for(Point position : chosenPositions) {
            tilesToBeInserted.add(board.removeItemTile(position));
        }

        game.setGameState(new InsertTilesState());

        return true;
    }

    /**
     * @return true if there isn't any columns with enough free cells to contain all the new tiles
     */
    private boolean tooManyTilesChosen(int size) {
        if(size>3)  return true;

        Shelf shelf = activePlayer.getShelf();
        for (int i = 0; i < shelf.getCOLUMNS(); i++) {
            if(shelf.getNumOfBoxLeftInCol(i) >= size)   return false;    //there is still enough free cell in at least a column
        }
        return true;    //not enough free cell in any columns
    }

    public boolean enoughFreeCellsInCol(int column) {
        return activePlayer.getShelf().getNumOfBoxLeftInCol(column) >= tilesToBeInserted.size();
    }

    /**
     * Method called by an observer when the activePlayer chooses the specific order they want to insert the tiles picked up
     *
     * @param tileIndex the index of the tile in the tilesToBeInserted array the activePLayer has chosen to insert into their Shelf
     * @param column the column selected
     * @return true if it was possible to insert the tile
     */
    public boolean sendTilesToShelf(int tileIndex, int column) throws NullItemTileException, FullColumnException {

        if(readyToInsert) {
            if(column == this.chosenColumn) {
                ItemTile tileToInsert = tilesToBeInserted.get(tileIndex);
                if(activePlayer.getShelf().insertTile(tileToInsert, column)) {
                  //TODO game.getTurnHandler().changeTurn();
                  game.setGameState(new PickUpTilesState());

                  return true;
                }
            }
        }
        return false;
    }

    public List<ItemTile> getTilesToBeInserted() {
        return tilesToBeInserted;
    }

    /**
     * @return the column of the personal Shelf which was chosen by the activePlayer to insert the tiles they picked up from the board
     */
    public int getChosenColumn() {
        return chosenColumn;
    }

    /**
     * @param chosenColumn the column of the personal Shelf which was chosen by the activeélayer to insert the tiles they picked up from the board
     */
    public void setChosenColumn(int chosenColumn) {
        this.chosenColumn = chosenColumn;
        readyToInsert = true;
    }
}
