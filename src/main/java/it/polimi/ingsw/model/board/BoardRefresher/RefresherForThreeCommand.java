package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;

public class RefresherForThreeCommand extends RefresherCommand {


    public RefresherForThreeCommand(Board board, Bag bag) {
        super(board, bag);
    }

    @Override
    public void refillBoard() {

        //TODO set only the tiles exclusive for 3 players
        if(board.getBoardGrid()[0][3] == null){
            board.getBoardGrid()[0][3] = BAG.drawSingleTile();
        }
        if(board.getBoardGrid()[2][3] == null){
            board.getBoardGrid()[2][2] = BAG.drawSingleTile();
        }


        // copy the second quadrant to the other quadrants
        fourQuadrantMirror();
    }
}
