package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashMap;

public class SendBoardMapper extends ServerMappable {


    @Override
    public void map(Object o) {
        if (!(o instanceof Board)) return;//error
        Board board = (Board) o;
        ItemTile[][] boardGrid = board.getBoardGrid();

        HashMap<String, String> map = encodeItemTilesGrid(boardGrid);

        //TODO send to the server socket

    }
}
