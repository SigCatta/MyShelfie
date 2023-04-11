package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashMap;

public class SendBoardMappable implements ServerMappable {

    @Override
    public void map(Object o) {
        if(! (o instanceof Board)) return;//error
        Board board = (Board) o;
        ItemTile[][] boardGrid = board.getBoardGrid();

        HashMap<String, String> map = new HashMap<>();

        for(int row = 0; row < boardGrid.length; row++){

            StringBuilder rowString = new StringBuilder();

            for(int col = 0; col < boardGrid.length; col++) {
                rowString.append(boardGrid[row][col].getColor().toString()).append(",");
            }

            map.put("ROW" + row, rowString.toString());
        }

    }
}
