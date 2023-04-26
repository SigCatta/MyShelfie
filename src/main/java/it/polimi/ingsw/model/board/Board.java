package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Controller.Server.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.Controller.Server.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board implements VirtualViewSubject {
    private ArrayList<VirtualViewObserver> observers;
    private final ItemTile[][] BOARD_GRID;

    public Board(int boardDimension) {
        BOARD_GRID = new ItemTile[boardDimension][boardDimension];
        observers = new ArrayList<>();
    }

    public ItemTile[][] getBoardGrid() {
        return BOARD_GRID;
    }

    public int getSize(){
        return BOARD_GRID.length;
    }

    public void setItemTile(Color color, int row, int col){
        if(row >= BOARD_GRID.length || col >= BOARD_GRID.length) return;
        BOARD_GRID[row][col] = new ItemTile(color);
        notifyObservers();
    }

    public ItemTile removeItemTile(Point location) {
        ItemTile pickedUpTile = BOARD_GRID[location.x][location.y];
        BOARD_GRID[location.x][location.y] = null;
        notifyObservers();
        return pickedUpTile;
    }

    public void emptyBoard(){
        for (ItemTile[] itemTiles : BOARD_GRID) {
            Arrays.fill(itemTiles, null);
        }
        notifyObservers();
    }

    @Override
    public void registerObserver(VirtualViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(VirtualViewObserver observer) {
        observers.remove(observer);
    }

    /**
     * this is also called in the board refresher when done refilling the board
     */
    @Override
    public void notifyObservers() {
        for(VirtualViewObserver observer : observers){
            observer.update();
        }
    }
}
