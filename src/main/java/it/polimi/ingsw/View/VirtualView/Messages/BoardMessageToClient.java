package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;

public class BoardMessageToClient implements MessageToClient, Serializable {
    private final ItemTile[][] BOARD;

    public BoardMessageToClient(Board board) {
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
