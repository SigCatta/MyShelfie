package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualView.ModelObservers.ModelObserver;
import it.polimi.ingsw.VirtualView.ModelObservers.ModelSubject;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board implements ModelSubject {
    private final ArrayList<ModelObserver> OBSERVERS;
    private final ItemTile[][] BOARD_GRID;

    public Board(int boardDimension) {
        BOARD_GRID = new ItemTile[boardDimension][boardDimension];
        OBSERVERS = new ArrayList<>();
    }

    public Board(ItemTile[][] board) { // only for testing
        BOARD_GRID = board;
        OBSERVERS = new ArrayList<>();
    }

    public ItemTile[][] getBoardGrid() {
        return BOARD_GRID;
    }


    public int getSize() {
        return BOARD_GRID.length;
    }

    public void setItemTile(Color color, int row, int col) { // only for testing
        if (row >= BOARD_GRID.length || col >= BOARD_GRID.length) return;
        BOARD_GRID[row][col] = new ItemTile(color);
        notifyObservers();
    }

    public ItemTile removeItemTile(Point location) {
        ItemTile pickedUpTile = BOARD_GRID[location.x][location.y];
        BOARD_GRID[location.x][location.y] = null;
        return pickedUpTile;
    }

    public ArrayList<ItemTile> removeItemTiles(ArrayList<Point> positions) {
        ArrayList<ItemTile> tiles = new ArrayList<>();
        positions.forEach(p -> tiles.add(removeItemTile(p)));

        notifyObservers();
        return tiles;
    }

    public void emptyBoard() {
        for (ItemTile[] itemTiles : BOARD_GRID) {
            Arrays.fill(itemTiles, null);
        }
        notifyObservers();
    }

    @Override
    public void registerObserver(ModelObserver observer) {
        OBSERVERS.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        OBSERVERS.remove(observer);
    }

    /**
     * this is also called in the board refresher when done refilling the board
     */
    @Override
    public void notifyObservers() {
        OBSERVERS.forEach(ModelObserver::update);
    }
}
