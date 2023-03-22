package it.polimi.ingsw.model;

import it.polimi.ingsw.model.gameItems.Tiles.ItemTile;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Shelf {
    private final int COLUMNS_SIZE = 5;
    private final int ROWS_SIZE = 6;
    private List<Stack<ItemTile>> shelfGrid;

    public Shelf() {
        initializeShelfGrid();
    }

    private void initializeShelfGrid() {
        shelfGrid = new ArrayList<>();
        for (int i = 0; i < COLUMNS_SIZE; i++) {
            shelfGrid.add(new Stack<>());
        }
    }

    public int getNumOfBoxLeftInCol(int column) {
        return ROWS_SIZE - shelfGrid.get(column).size();
    }
    public boolean isColumnFull(int column) {
        return getNumOfBoxLeftInCol(column)==0;
    }

    public ItemTile getTileAtLocation(Point location) {
        if(shelfGrid.get(location.y).size() <= location.x)  return null;

        return shelfGrid.get(location.y).get(location.x);
    }

    public Stack<ItemTile> getShelfGridColumn(int column) {
        return shelfGrid.get(column);
    }

    public List<Stack<ItemTile>> getShelfGrid() {
        return shelfGrid;
    }

    public int getCOLUMNS_SIZE() {
        return COLUMNS_SIZE;
    }

    public int getROWS_SIZE() {
        return ROWS_SIZE;
    }
}
