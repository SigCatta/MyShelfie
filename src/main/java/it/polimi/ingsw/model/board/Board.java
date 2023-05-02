package it.polimi.ingsw.model.board;

import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board implements VirtualViewSubject {
    private final ArrayList<VirtualViewObserver> OBSERVERS;
    private final ItemTile[][] BOARD_GRID;
    private final Color[][] COLOR_GRID;

    public Board(int boardDimension) {
        BOARD_GRID = new ItemTile[boardDimension][boardDimension];
        COLOR_GRID = new Color[boardDimension][boardDimension];
        OBSERVERS = new ArrayList<>();
    }

    public Board(ItemTile[][] board){
        BOARD_GRID = board;
        COLOR_GRID = toColorArray(board);
        OBSERVERS = new ArrayList<>();
    }

    public ItemTile[][] getBoardGrid() {
        return BOARD_GRID;
    }

    public Color[][] getColorGrid(){
        return COLOR_GRID;
    }

    public int getSize(){
        return BOARD_GRID.length;
    }

    public void setItemTile(Color color, int row, int col){
        if(row >= BOARD_GRID.length || col >= BOARD_GRID.length) return;
        BOARD_GRID[row][col] = new ItemTile(color);
        COLOR_GRID[row][col] = color;
        notifyObservers();
    }

    public ItemTile removeItemTile(Point location) {
        ItemTile pickedUpTile = BOARD_GRID[location.x][location.y];
        BOARD_GRID[location.x][location.y] = null;
        COLOR_GRID[location.x][location.y] = null;
        notifyObservers();
        return pickedUpTile;
    }

    public ArrayList<ItemTile> removeItemTiles(ArrayList<Point> positions){
        ArrayList<ItemTile> tiles = new ArrayList<>();
        for(Point p : positions){
            tiles.add(removeItemTile(p));
        }
        return tiles;
    }

    public void emptyBoard(){
        for (ItemTile[] itemTiles : BOARD_GRID) {
            Arrays.fill(itemTiles, null);
        }
        notifyObservers();
    }

    private Color[][] toColorArray(ItemTile[][] boardGrid){
        Color[][] colorGrid = new Color[boardGrid.length][boardGrid[0].length];
        for(int i = 0; i < boardGrid.length; i++){
            for(int j = 0; j < boardGrid[0].length; j++){
                if(boardGrid[i][j] == null) continue;
                colorGrid[i][j] = boardGrid[i][j].getColor();
            }
        }
        return colorGrid;
    }

    @Override
    public void registerObserver(VirtualViewObserver observer) {
        OBSERVERS.add(observer);
    }

    @Override
    public void removeObserver(VirtualViewObserver observer) {
        OBSERVERS.remove(observer);
    }

    /**
     * this is also called in the board refresher when done refilling the board
     */
    @Override
    public void notifyObservers() {
        for(VirtualViewObserver observer : OBSERVERS){
            observer.update();
        }
    }
}
