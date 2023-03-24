package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;
import it.polimi.ingsw.model.tiles.ItemTile;

public abstract class RefresherCommand {

    protected Board board;
    protected final Bag BAG;
    protected int boardSize;
    protected ItemTile[][] boardGrid;

    public RefresherCommand(Board board, Bag bag){
        this.board = board;
        this.BAG = bag;

        boardSize = board.getBoardGrid().length;
        boardGrid = board.getBoardGrid();
    }

    /**
     * This method is used to create a four-quadrant symmetrical board
     * starting from the top left quadrant already filled
     */
    protected void fourQuadrantMirror() {
        for (int i = 0; i < boardSize / 2; i++) {
            for (int j = 0; j < boardGrid[i].length / 2; j++) {

                if (boardGrid[i][j] != null) {
                    continue;
                }

                // top right quadrant
                boardGrid[i][boardSize - 1 - j] = BAG.drawSingleTile();
                // bottom left quadrant
                boardGrid[boardSize - 1 - i][j] = BAG.drawSingleTile();
                // bottom right quadrant
                boardGrid[boardSize - 1 - i][boardSize - 1 - j] = BAG.drawSingleTile();
            }
        }
    }

    abstract void refillBoard();
}
