package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;
import it.polimi.ingsw.model.tiles.ItemTile;

public class RefresherForTwoCommand extends RefresherCommand {


    public RefresherForTwoCommand(Board board, Bag bag) {
        super(board, bag);
    }


    /**
     *
     * This method refills the board for two players,
     * since the positions are symmetrical the first loop instantiates the
     * top left quadrant of the matrix, the second loop extends it to all
     */
    @Override
    public void refillBoard() {
        //TODO set all the tiles possible following the rules

        for (int r = 0; r < boardSize / 2; r++) {
            for (int c = 0; c < boardSize / 2; c++) {
                if (boardGrid[r][c] != null || (r + c < 5 && !(r == 1 && c == 3) ) ) {
                    continue;
                }
                boardGrid[r][c] = BAG.drawSingleTile();
            }
        }


        // copy the second quadrant to the other quadrants
        fourQuadrantMirror();

    }
}
