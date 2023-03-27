package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;


public class RefresherHandler {
    private final Board board;
    private final int BOARD_SIZE;
    private final Bag bag;

    RefresherHandler(Board board, Bag bag){
        this.board = board;
        this.bag = bag;

        BOARD_SIZE = board.getBoardGrid().length;
    }

    /**
     * fills the board
     */
    public void refillBoard(boolean[][] playableSquares) {

        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {

                if(playableSquares[r][c] && board.getBoardGrid()[r][c] != null) {
                    board.getBoardGrid()[r][c] = bag.drawSingleTile();
                }
            }
        }
    }

}
