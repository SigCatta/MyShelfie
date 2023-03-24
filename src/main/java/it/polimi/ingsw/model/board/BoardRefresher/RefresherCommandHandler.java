package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;


public class RefresherCommandHandler {
    private final boolean[][] pointsToBeFilled = new boolean[9][9];
    private final Board board;
    private final Bag bag;

    RefresherCommandHandler(Board board, Bag bag){
        this.board = board;
        this.bag = bag;
    }

    // Add command to the list
    public void addPointsInformation(BoardLookUpTable command) {
        if(command.getPointsToBeFilled().length != pointsToBeFilled.length) return;

        for (int r = 0; r < pointsToBeFilled.length; r++) {
            for (int c = 0; c < pointsToBeFilled.length; c++) {

                pointsToBeFilled[r][c] = pointsToBeFilled[r][c] || command.getPointsToBeFilled()[r][c];

            }
        }
    }

    // Execute every method refillBoard of the instances in the list
    public void executeCommands() {
        for (int r = 0; r < pointsToBeFilled.length; r++) {
            for (int c = 0; c < pointsToBeFilled.length; c++) {

                if(pointsToBeFilled[r][c]){
                    board.getBoardGrid()[r][c] = bag.drawSingleTile();
                }

            }
        }
    }

}