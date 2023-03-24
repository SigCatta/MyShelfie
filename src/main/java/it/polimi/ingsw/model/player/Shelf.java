package it.polimi.ingsw.model.player;


import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A class representing the player's shelf.
 */
public class Shelf {

    /**
     * The number of columns in the shelf grid.
     */
    private final int COLUMNS = 5;

    /**
     * The number of rows in the shelf grid.
     */
    private final int ROWS = 6;

    /**
     * The grid of stacks of ItemTiles representing the player's shelf.
     */
    private List<Stack<ItemTile>> shelfGrid;

    /**
     * Constructs a new Shelf object and initializes the shelf grid.
     */
    public Shelf() {
        initializeShelfGrid();
    }

    /**
     * Initializes the shelf grid by creating a stack for each column.
     */
    private void initializeShelfGrid() {
        shelfGrid = new ArrayList<>();
        for (int i = 0; i < COLUMNS; i++) {
            shelfGrid.add(new Stack<>());
        }
    }

    /**
     * @param column The column number (starting from 0).
     * @return The number of box left in the column.
     */
    public int getNumOfBoxLeftInCol(int column) {
        return ROWS - shelfGrid.get(column).size();
    }

    /**
     * @param column The column number (starting from 0).
     * @return True if the column is full, false otherwise.
     */
    public boolean isColumnFull(int column) {
        return getNumOfBoxLeftInCol(column) == 0;
    }

    /**
     * Returns the ItemTile at the specified location in the shelf grid. Returns null if there
     * is no ItemTile at the specified location.
     * @param location The Point representing the location of the ItemTile.
     * @return The ItemTile at the specified location or null if there is none.
     */
    public ItemTile getTileAtLocation(Point location) {
        if (shelfGrid.get(location.y).size() <= location.x) {
            return null;
        }
        return shelfGrid.get(location.y).get(location.x);
    }

    /**
     * Sets the ItemTile at the specified location in the shelf grid.
     * @param point The Point representing the location of the ItemTile.
     * @param itemTile The ItemTile to be set.
     */
    private void setTileAtLocation(Point point, ItemTile itemTile) {
        if (point.y >= 0 && point.y < COLUMNS) {
            Stack<ItemTile> columnStack = shelfGrid.get(point.y);
            if (point.x < columnStack.size()) {
                columnStack.set(point.x, itemTile);
            } else {
                while (columnStack.size() < point.x) {
                    columnStack.push(null);
                }
                columnStack.push(itemTile);
            }
        }
    }

    /**
     * Returns the stack of ItemTiles in the specified column of the shelf grid.
     * @param column The column number (starting from 0).
     * @return The stack of ItemTiles in the column.
     */
    public Stack<ItemTile> getShelfGridColumn(int column) {
        return shelfGrid.get(column);
    }

    /**
     * @return The List of stacks of ItemTiles representing the player's shelf.
     */
    public List<Stack<ItemTile>> getShelfGrid() {
        return shelfGrid;
    }

    /**
     * @return The number of columns in the shelf grid.
     */
    public int getCOLUMNS() {
        return COLUMNS;
    }

    /**
     * @return The number of rows in the shelf grid.
     */
    public int getROWS() {
        return ROWS;
    }
}