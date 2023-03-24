package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Bag;

import java.util.ArrayList;


public class BoardRefresher {

    private Board board;
    private Bag bag;

    private ArrayList<Player> players;

    public BoardRefresher(Game game) {
        players = game.getPlayers();

        board = game.getBoard();
        bag = game.getBag();

    }

    /**
     * modified version of COMMAND design pattern
     * the handler merges the previous lookup table
     * with the lookup table that is added with the
     * method addPointsInformation
     */
    public void refillBoard(){

        RefresherHandler handler = new RefresherHandler(board, bag);

        handler.addPointsInformation(new BoardLookUpTableTwo());
        if(players.size() >= 3){
            handler.addPointsInformation(new BoardLookUpTableThree());
        }
        if(players.size() >= 4){
            handler.addPointsInformation(new BoardLookUpTableFour());
        }

        handler.executeCommands();
    }

}
