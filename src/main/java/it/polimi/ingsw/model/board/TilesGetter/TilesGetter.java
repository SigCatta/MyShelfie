package it.polimi.ingsw.model.board.TilesGetter;

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
    private Game game;
    /**
     * The list of ItemTiles to be inserted.
     */
    private List<ItemTile> tilesToBeInserted ;
    /**
     * every item in the list is linked to the ItemTile in the tilesToBeInserted list,
     * and it is true if it has already been sent to the shelf, false otherwise
     */
    private List<Boolean> positionsAlreadySelected;

    public TilesGetter(Game game){
        this.game = game;
        PICK_UP_VALIDATOR = new PickUpValidator(game);
        board = game.getBoard();
        tilesToBeInserted = new ArrayList<>();
        positionsAlreadySelected = new ArrayList<>();
    }

    /**
     * @param chosenPositions The list of cells on the board chosen by the player
     * @return true if the positions are valid and there are enough free cell left in the player's shelf
     */
    public boolean pickUpTiles(ArrayList<Point> chosenPositions){

        this.tilesToBeInserted = new ArrayList<>();
        this.positionsAlreadySelected = new ArrayList<>();
        this.chosenColumn = null;

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
            positionsAlreadySelected.add(false);
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
            if(shelf.getNumOfBoxLeftInCol(i) >= size)   return false;    //there is still enough free cell in at least a column
        }
        return true;    //not enough free cell in any columns
    }

    private boolean enoughFreeCellsInCol(int column) {
        return game.getActivePlayer().getShelf().getNumOfBoxLeftInCol(column) >= tilesToBeInserted.size();
    }

    /**
     * Method called by an observer when the activePlayer chooses the specific order they want to insert the tiles picked up
     *
     * @param tileIndex the index of the tile in the tilesToBeInserted array the activePLayer has chosen to insert into their Shelf
     * @param column the column selected
     * @return true if it was possible to insert the tile,
     *         false if the column selected isn't the corrected one or if the itemTile was already inserted
     */
    public boolean sendTilesToShelf(int tileIndex, int column) {

        activePlayer = game.getActivePlayer();

        if(tileIndex >= positionsAlreadySelected.size() || tileIndex < 0) return false;

        //check if the number of tiles to insert fit in the chosen column
        if(this.chosenColumn == null && !enoughFreeCellsInCol(column)) return false;


        //here the chosen column is definitive
        if(this.chosenColumn == null) this.chosenColumn = column;

        //check if the player chosen column is the right one and if the tile selected was not selected before
        if(column != this.chosenColumn || positionsAlreadySelected.get(tileIndex)) return false;

        ItemTile tileToInsert = tilesToBeInserted.get(tileIndex);
        if(!activePlayer.getShelf().insertTile(tileToInsert, column)) return false; //should not reach

        positionsAlreadySelected.set(tileIndex, true);

        //there are no more tiles to be inserted because the positions are all selected
        if(positionsAlreadySelected.stream().allMatch(val -> val)){
            game.getTurnHandler().changeTurn();
            game.setGameState(new PickUpTilesState());
        }

        return true;
    }

    public List<ItemTile> getTilesToBeInserted() {
        return tilesToBeInserted;
    }

    public List<Boolean> getPositionsAlreadySelected() {
        return positionsAlreadySelected;
    }

    /**
     * @return the column of the personal Shelf which was chosen by the activePlayer to insert the tiles they picked up from the board
     */
    public int getChosenColumn() {
        return chosenColumn;
    }
}
