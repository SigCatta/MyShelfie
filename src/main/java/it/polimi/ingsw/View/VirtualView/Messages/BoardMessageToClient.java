package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Color;

import java.io.Serializable;

public class BoardMessageToClient implements MessageToClient, Serializable {
    private final Color[][] BOARD;

    public BoardMessageToClient(Board board){
        this.BOARD = board.getColorGrid();
    }

    public Color[][] getColorBoard() {
        return BOARD;
    }

    @Override
    public void update() {
        ClientController.getInstance().changeBoard(this);
    }
}
