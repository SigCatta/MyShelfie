package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Color;

import java.io.Serializable;

public class BoardMessage implements Message, Serializable {
    private final Color[][] BOARD;

    public BoardMessage(Board board){
        this.BOARD = board.getColorGrid();
    }

    public Color[][] getColorBoard() {
        return BOARD;
    }

    @Override
    public void update() {
        Controller.getInstance().changeBoard(this);
    }
}
