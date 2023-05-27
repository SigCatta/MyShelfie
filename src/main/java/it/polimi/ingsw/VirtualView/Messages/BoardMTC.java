package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;

/**
 * This class represents a message to the client containing the current state of the game board.
 */
public class BoardMTC implements MessageToClient, Serializable {
    private final ItemTile[][] BOARD;

    public BoardMTC(Board board) {
        this.BOARD = board.getBoardGrid();
    }

    public ItemTile[][] getColorBoard() {
        return BOARD;
    }

    @Override
    public void update() {
        BoardRepresentation.getInstance().setBoard(this);
    }
}
