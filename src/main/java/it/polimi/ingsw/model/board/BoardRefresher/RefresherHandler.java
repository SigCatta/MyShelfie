package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;


public class RefresherHandler {
    private final boolean[][] pointsToBeFilled;
    private final Board board;
    private final int BOARD_SIZE;
    private final Bag bag;

    RefresherHandler(Board board, Bag bag) {
        this.board = board;
        this.bag = bag;

        BOARD_SIZE = board.getBoardGrid().length;
        pointsToBeFilled = new boolean[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * uses the lookUpTable to change the pointsToBeFilled matrix,
     * this it holds true only in the positions where the tile is needed
     *
     * @param lookUpTable matrix
     */
    public void addPointsInformation(BoardLookUpTable lookUpTable) {
        if (lookUpTable.getPointsToBeFilled().length != pointsToBeFilled.length) return;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {

                pointsToBeFilled[row][column] = pointsToBeFilled[row][column] || lookUpTable.getPointsToBeFilled()[row][column];

            }
        }
    }

    /**
     * fills the board
     */
    public void executeCommands() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {

                if (pointsToBeFilled[row][column]) {
                    board.getBoardGrid()[row][column] = bag.drawSingleTile();
                }

            }
        }
    }

}