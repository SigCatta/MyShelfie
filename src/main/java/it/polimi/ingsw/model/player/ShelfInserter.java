package it.polimi.ingsw.model.player;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
import exceptions.NullPlayersException;
import exceptions.TooManyTilesException;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.List;
import java.util.Stack;

/**
 * A class that manages the logic to fill the player's shelf.
 */
public class ShelfInserter {
    private Shelf shelf;
    private ShelfBuffer shelfBuffer;
    private Player activePlayer;

    public ShelfInserter() {
        activePlayer = null;
        shelfBuffer = new ShelfBuffer();
    }

    public ShelfBuffer getShelfBuffer() {
        return shelfBuffer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
        this.shelf = activePlayer.getShelf();
    }

    /**
     * @return the Shelf object managed by this ShelfInserter instance.
     */
    public Shelf getShelf() throws NullPlayersException {
        if(activePlayer == null)    throw new NullPlayersException();
        return activePlayer.getShelf();
    }

    /**
     * Inserts a list of ItemTile objects onto a specified column of the shelf grid.
     * @param column the column on which to insert the tiles.
     * @return true if the tiles were successfully inserted, false otherwise.
     * @exception NullItemTileException: if the buffer is empty
     * @exception FullColumnException: if the column selected is already full
     * @exception TooManyTilesException: if there is not enough room in the selected column
     */
    public boolean insertTiles(int column) throws NullItemTileException, FullColumnException, TooManyTilesException {
        //the list of ItemTile objects to insert.
        List<ItemTile> tiles = shelfBuffer.getTiles();
        if(tiles == null) {
            throw new NullItemTileException();
        }

        if(shelfBuffer.isOrdered()) {
            if(shelf.isColumnFull(column))  throw  new FullColumnException();
            if(shelf.getNumOfBoxLeftInCol(column) < tiles.size()) throw new TooManyTilesException();     //no more room in column

            int tileIndex = 0 ;
            for (int i = 0; i < shelf.getROWS(); i++) {
                Point location = new Point(i, column);
                if(shelf.getTileAtLocation(location) == null) {
                    if(tiles.get(tileIndex) == null) throw new NullItemTileException();
                    shelf.setTileAtLocation(location, tiles.get(tileIndex)) ;
                    tileIndex++;
                }
                if(tileIndex == tiles.size() - 1)   break;
            }
            return true;
        } else {
            return false;
        }
    }
}