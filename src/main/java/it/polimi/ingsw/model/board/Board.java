package it.polimi.ingsw.model.board;

import it.polimi.ingsw.ReadFromJSONFile;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Board {
    private final ItemTile[][] BOARD_GRID;
    private final boolean[][] playableSquares;

    public Board(int boardDimension, int numOfPlayers) throws IOException, ParseException {
        BOARD_GRID = new ItemTile[boardDimension][boardDimension];
        playableSquares = new ReadFromJSONFile().getPlayableSquares(numOfPlayers, boardDimension);
    }

    public ItemTile[][] getBoardGrid() {
        return BOARD_GRID;
    }

    public boolean[][] getPlayableSquares() {
        return playableSquares;
    }
}
