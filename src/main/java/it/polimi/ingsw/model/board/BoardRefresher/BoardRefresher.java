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

    public void refillBoard(){

        RefresherCommandHandler handler = new RefresherCommandHandler();

        handler.addCommand(new RefresherForTwoCommand(board, bag));
        if(players.size() >= 3){
            handler.addCommand(new RefresherForThreeCommand(board, bag));
        }
        if(players.size() >= 4){
            handler.addCommand(new RefresherForFourCommand(board, bag));
        }

        handler.executeCommands();
    }

}
