package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Bag;

import java.util.ArrayList;


public class BoardRefresher {

    private Board board;
    private Bag bag;

    public BoardRefresher(Game game) {

        board = game.getBoard();
        bag = game.getBag();
    }

    /**
     * modified version of COMMAND design pattern,
     * the handler merges the previous lookup table
     * with the lookup table that is added with the
     * method addPointsInformation
     */
    public void refillBoard() {

        new RefresherHandler(board, bag).refillBoard(board.getPlayableSquares());
    }

}
