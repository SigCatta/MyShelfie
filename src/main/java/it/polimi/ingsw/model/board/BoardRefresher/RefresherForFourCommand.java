package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;

public class RefresherForFourCommand extends RefresherCommand {


    public RefresherForFourCommand(Board board, Bag bag) {
        super(board, bag);
    }

    @Override
    public void refillBoard() {

        if(board.getBoardGrid()[0][4] == null){
            board.getBoardGrid()[0][4] = BAG.drawSingleTile();
        }
        if(board.getBoardGrid()[2][5] == null){
            board.getBoardGrid()[2][5] = BAG.drawSingleTile();
        }

        // copy the second quadrant to the other quadrants
        fourQuadrantMirror();
    }
}
