package it.polimi.ingsw.Controller.Server.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;
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

    @Override
    public void update() {
        Controller.getInstance().changeBoard(this);
    }
}
