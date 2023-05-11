package it.polimi.ingsw.model.player;


import it.polimi.ingsw.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class representing the player's shelf.
 */
public class Shelf implements VirtualViewSubject {

    private final ArrayList<VirtualViewObserver> observers;

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
     * 0 |
     * 1 |
     * 2 |
     * 3 |
     * 4 |
     * 5 | _ _ _ _ _
     *     0 1 2 3 4
     */
    private final ItemTile[][] shelfGrid;

    /**
     * Constructs a new Shelf object
     */
    public Shelf() {
        observers = new ArrayList<>();
        shelfGrid = new ItemTile[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            shelfGrid[i] = new ItemTile[COLUMNS];
            Arrays.fill(shelfGrid[i], null);
        }
    }

    public Shelf(ItemTile[][] matrix) {
        observers = new ArrayList<>();
        shelfGrid = matrix;
    }

    /**
     * Returns the ItemTile at the specified location in the shelf grid. Returns null if there
     * is no ItemTile at the specified location.
     *
     * @param location The Point representing the location of the ItemTile.
     * @return The ItemTile at the specified location or null if there is none.
     */
    public ItemTile getTileAtLocation(Point location) {
        return shelfGrid[location.x][location.y];
    }

    public void setTileAtLocation(Point location, ItemTile tile) {
        shelfGrid[location.x][location.y] = tile;
        notifyObservers();
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
        int nullCellCount = 0;

        for (int i = 0; i < ROWS; i++) {
            if (shelfGrid[i][column] == null)
                nullCellCount++;
        }

        return nullCellCount;
    }

    /**
     * @param column The column number (starting from 0).
     * @return True if the column is full, false otherwise.
     */
    public boolean isColumnFull(int column) {
        return shelfGrid[0][column] != null;
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

    /**
     * Inserts a list of ItemTile objects onto a specified column of the shelf grid.
     *
     * @param tile   the tile to insert
     * @param column the column on which to insert the tiles.
     * @return true if the tiles were successfully inserted, false otherwise.
     */
    public boolean insertTile(ItemTile tile, int column){
        if (tile == null) {
            //TODO update the virtual view to send the error message to the user
            return false;
        }
        if (isColumnFull(column)) {
            //TODO update the virtual view to send the error message to the user
            return false;
        }

        for (int i = ROWS - 1; i >= 0; i--) {
            Point location = new Point(i, column);
            if (getTileAtLocation(location) == null) {
                setTileAtLocation(location, tile);
                break;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < COLUMNS; i++) {
            if (!isColumnFull(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void registerObserver(VirtualViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(VirtualViewObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(VirtualViewObserver observer : observers){
            observer.update();
        }
    }
}
