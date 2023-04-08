package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.Arrays;

public class Board {
    private final ItemTile[][] BOARD_GRID;

    public Board(int boardDimension) {
        BOARD_GRID = new ItemTile[boardDimension][boardDimension];
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
    }

    public ItemTile removeItemTile(Point location) {
        ItemTile pickedUpTile = BOARD_GRID[location.x][location.y];
        BOARD_GRID[location.x][location.y] = null;
        return pickedUpTile;
    }

    public void emptyBoard(){
        for (ItemTile[] itemTiles : BOARD_GRID) {
            Arrays.fill(itemTiles, null);
        }
    }

}
