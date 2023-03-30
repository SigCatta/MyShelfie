package it.polimi.ingsw.model.player;


import exceptions.NullItemTileException;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
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
     * The grid of ItemTiles representing the player's shelf.
     *  5 |
     *  4 |
     *  3 |
     *  2 |
     *  1 |
     *  0 | _ _ _ _ _
     *      0 1 2 3 4
     */
    private ItemTile[][] shelfGrid;

    /**
     * Constructs a new Shelf object
     */
    public Shelf() {
        shelfGrid = new ItemTile[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            shelfGrid[i] = new ItemTile[COLUMNS];
            for (int j = 0; j < COLUMNS; j++) {
                shelfGrid[i][j] = null;
            }
        }
    }
    public Shelf(ItemTile[][] matrix) {
        shelfGrid = matrix;
    }

    /**
     * Returns the ItemTile at the specified location in the shelf grid. Returns null if there
     * is no ItemTile at the specified location.
     * @param location The Point representing the location of the ItemTile.
     * @return The ItemTile at the specified location or null if there is none.
     */
    public ItemTile getTileAtLocation(Point location) {
        return shelfGrid[location.x][location.y];
    }

    public void setTileAtLocation(Point location, ItemTile tile) {
        shelfGrid[location.x][location.y] = tile;
    }


    /**
     * @return The List of stacks of ItemTiles representing the player's shelf.
     */
    public ItemTile[][] getShelfGrid() {
        return shelfGrid;
    }

    /**
     * @param column The column number (starting from 0).
     * @return The number of box left in the column.
     */
    public int getNumOfBoxLeftInCol(int column) {
        int nullCellcount = 0;

        for (int i = 0; i < ROWS; i++) {
            if(shelfGrid[i][column]==null)
                nullCellcount++;
        }

        return nullCellcount;
    }

    /**
     * @param column The column number (starting from 0).
     * @return True if the column is full, false otherwise.
     */
    public boolean isColumnFull(int column) {
        return shelfGrid[ROWS-1][column] != null;
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

    public Color[][] generateColorMat(){
        return ShelfUtils.generateColorMat(shelfGrid, getROWS(), getCOLUMNS());
    }
}