package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.List;
import java.util.Stack;

/**
 * A class that manages the logic to fill the player's shelf.
 */
public class ShelInserter {
    private Shelf shelf;

    /**
     * Constructs a new ShelInserter object and initializes the shelf field with a new instance of the Shelf class.
     */
    public ShelInserter(){
        shelf = new Shelf();
    }

    /**
     * @return the Shelf object managed by this ShelInserter instance.
     */
    public Shelf getShelf() {
        return shelf;
    }

    /**
     * Inserts a list of ItemTile objects onto a specified column of the shelf grid.
     * @param column the column on which to insert the tiles.
     * @param tiles the list of ItemTile objects to insert.
     * @return true if the tiles were successfully inserted, false otherwise.
     */
    public boolean insertTiles(int column, List<ItemTile> tiles){
        if(shelf.isColumnFull(column))  return false;
        if(shelf.getNumOfBoxLeftInCol(column) < tiles.size()) return false;     //no more room in column

        Stack<ItemTile> tilesStack = getShelfGridTilesAtColumn(column);
        for (int i = 0; i < tiles.size(); i++) {
            tilesStack.add(tiles.get(i));
        }
        return true;
    }

    /**
     * Returns a Stack of ItemTile objects representing the grid of the shelf at a specified column.
     * @param column the column of the grid to retrieve.
     * @return a Stack of ItemTile objects representing the column of ItemTiles of the shelf at the specified column.
     */
    public Stack<ItemTile> getShelfGridTilesAtColumn(int column) {
        return  shelf.getShelfGridColumn(column);
    }
}