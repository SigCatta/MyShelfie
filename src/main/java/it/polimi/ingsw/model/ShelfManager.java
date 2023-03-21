package it.polimi.ingsw.model;

import it.polimi.ingsw.model.gameItems.Tiles.ItemTile;

import java.util.List;
import java.util.Stack;

public class ShelfManager {
    private Shelf shelf;

    public ShelfManager(){
        shelf = new Shelf();
    }

    public Shelf getShelf() {
        return shelf;
    }

    public boolean insertTiles(int column, List<ItemTile> tiles){
        if(shelf.isColumnFull(column))  return false;
        if(shelf.getNumOfBoxLeftInCol(column) < tiles.size()) return false;     //no more room in column

        Stack<ItemTile> tilesStack = shelf.getShelfGridColumn(column);
        for (int i = 0; i < tiles.size()-1; i++) {
            tilesStack.add(tiles.get(i));
        }
        return true;
    }
}
