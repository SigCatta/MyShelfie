package it.polimi.ingsw.model.board.BoardRefresher;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;


public class BoardRefresher {
    private final int PLAYERS_NUMBER;

    private Board board;
    private Bag bag;

    private int missingTilesNumber;

    public BoardRefresher(Game game) {
        PLAYERS_NUMBER = game.getPlayers().size();

        board = game.getBoard();
        bag = game.getBag();

        missingTilesNumber = (new MissingTilesCounter(board)).countMissingTiles();

    }

    public void refillBoard(){

        RefresherCommandHandler handler = new RefresherCommandHandler();

        handler.addCommand(new RefresherForTwoCommand());
        if(PLAYERS_NUMBER >= 3){
            handler.addCommand(new RefresherForThreeCommand());
        }
        if(PLAYERS_NUMBER >= 4){
            handler.addCommand(new RefresherForFourCommand());
        }

        handler.executeCommands();
    }

}
