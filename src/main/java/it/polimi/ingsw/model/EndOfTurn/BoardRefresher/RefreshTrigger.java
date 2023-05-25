package it.polimi.ingsw.model.EndOfTurn.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;

public class RefreshTrigger {

    /**
     * Checks if the board has only isolated tiles
     */
    public static boolean isBoardRefreshable(Board board) {
        ItemTile[][] boardGrid = board.getBoardGrid();

        for (int i = 0; i < boardGrid.length; i++) {
            for (int j = 0; j < boardGrid[i].length; j++) {

                if (boardGrid[i][j] == null) continue;

                if (i > 0 && isNotNull(boardGrid[i - 1][j])) return false;
                if (i < boardGrid.length - 1 && isNotNull(boardGrid[i + 1][j])) return false;
                if (j > 0 && isNotNull(boardGrid[i][j - 1])) return false;
                if (j < boardGrid[i].length - 1 && isNotNull(boardGrid[i][j + 1])) return false;
            }
        }
        return true;
    }

    private static boolean isNotNull(ItemTile tile) {
        return tile != null;
    }

}