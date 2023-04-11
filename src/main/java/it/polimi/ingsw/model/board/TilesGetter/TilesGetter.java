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
    }

    public void setActivePlayer(Player activePlayer) {
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
        Shelf shelf = activePlayer.getShelf();
        for (int i = 0; i < shelf.getCOLUMNS(); i++) {
            if(shelf.getNumOfBoxLeftInCol(i) >= size)   return false;    //there is still enough free cell in at least a column
        }
        return true;    //not enough free cell in any columns
    }

    public boolean enoughFreeCellsInCol(int column) {
        //TODO: maybe to move to a controller
        return activePlayer.getShelf().getNumOfBoxLeftInCol(column) >= tilesToBeInserted.size();
    }

    public boolean sendTilesToShelf(ItemTile tileToInsert, int column) throws NullItemTileException, FullColumnException {
        //TODO maybe move method in a controller
        if(activePlayer.getShelf().insertTile(tileToInsert, column)){

            //TODO game.getTurnHandler().changeTurn();
            game.setGameState(new PickUpTilesState());

            return true;
        }
        //TODO send a message to tell the user to change column
        return false;
    }

    public List<ItemTile> getTilesToBeInserted() {
        return tilesToBeInserted;
    }
}
