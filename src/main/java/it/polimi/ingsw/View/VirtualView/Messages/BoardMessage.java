package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;

public class BoardMessage implements Message, Serializable {
    private final ItemTile[][] BOARD;

    public BoardMessage(Board board){
        this.BOARD = board.getBoardGrid();
    }

    public ItemTile[][] getBoard() {
        return BOARD;
    }
}
