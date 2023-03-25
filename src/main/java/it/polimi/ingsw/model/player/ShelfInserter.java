package it.polimi.ingsw.model.player;

import exceptions.NullItemTileException;
import it.polimi.ingsw.model.tiles.ItemTile;

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
    public Shelf getShelf() {
        return activePlayer == null ? null : activePlayer.getShelf();
    }

    /**
     * Inserts a list of ItemTile objects onto a specified column of the shelf grid.
     *
     * @param column the column on which to insert the tiles.
     * @return true if the tiles were successfully inserted, false otherwise.
     * @throws NullItemTileException if there are no tiles to be inserted
     */
    public boolean insertTiles(int column) throws NullItemTileException {
        //the list of ItemTile objects to insert.
        List<ItemTile> tiles = shelfBuffer.getTiles();
        if(tiles == null) throw new NullItemTileException();

        if(shelfBuffer.isOrdered()) {
            if(shelf.isColumnFull(column))  return false;
            if(shelf.getNumOfBoxLeftInCol(column) < tiles.size()) return false;     //no more room in column

            Stack<ItemTile> tilesStack = getShelfGridColumn(column);
            tilesStack.addAll(tiles);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns a Stack of ItemTile objects representing the grid of the shelf at a specified column.
     * @param column the column of the grid to retrieve.
     * @return a Stack of ItemTile objects representing the column of ItemTiles of the shelf at the specified column.
     */
    public Stack<ItemTile> getShelfGridColumn(int column) {
        return  shelf.getShelfGridColumn(column);
    }
}