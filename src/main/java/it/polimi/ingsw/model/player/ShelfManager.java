package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.tiles.ItemTile;

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

        Stack<ItemTile> tilesStack = getShelfGridTilesAtColumn(column);
        for (int i = 0; i < tiles.size(); i++) {
            tilesStack.add(tiles.get(i));
        }
        return true;
    }

    public Stack<ItemTile> getShelfGridTilesAtColumn(int column) {
        return  shelf.getShelfGridColumn(column);
    }
}