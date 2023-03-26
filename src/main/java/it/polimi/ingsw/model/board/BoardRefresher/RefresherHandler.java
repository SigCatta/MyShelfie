package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;


public class RefresherHandler {
    private boolean[][] pointsToBeFilled;
    private final Board board;
    private final int BOARD_SIZE;
    private final Bag bag;

    RefresherHandler(Board board, Bag bag){
        this.board = board;
        this.bag = bag;

        BOARD_SIZE = board.getBoardGrid().length;
        pointsToBeFilled = new boolean[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * uses the lookUpTable to change the pointsToBeFilled matrix,
     * this it holds true only in the positions where the tile is needed
     * @param lookUpTable matrix
     */
    public void addPointsInformation(BoardLookUpTable lookUpTable) {
        if(lookUpTable.getPointsToBeFilled().length != pointsToBeFilled.length) return;

        pointsToBeFilled = lookUpTable.getPointsToBeFilled();
    }

    /**
     * fill the board
     */
    public void executeCommands() {
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {

                if(board.getBoardGrid()[r][c] != null) continue;

                if(pointsToBeFilled[r][c]){
                    board.getBoardGrid()[r][c] = bag.drawSingleTile();
                }

            }
        }
    }

}
