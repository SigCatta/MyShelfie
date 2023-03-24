package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.tiles.ItemTile;

public class Board {
    private final ItemTile[][] BOARD_GRID;

    public Board(int boardDimension){
        BOARD_GRID = new ItemTile[boardDimension][boardDimension];
    }

    public ItemTile[][] getBoardGrid() {
        return BOARD_GRID;
    }
}
