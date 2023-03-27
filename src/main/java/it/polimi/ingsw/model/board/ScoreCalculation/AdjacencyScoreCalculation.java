package it.polimi.ingsw.model.board.ScoreCalculation;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

public class AdjacencyScoreCalculation {
    //TODO it might be useful to have an interface for the classes that calculate the score

    private final Game GAME;
    private Player activePlayer;
    private Shelf activeShelf;

    public AdjacencyScoreCalculation(Game game){
        GAME = game;
    }

    /**
     * calculate the points gained from the adjacency of tiles
     * in the shelf
     * @return points
     */
    public int clalculateScore(){
        activePlayer = GAME.getActivePlayer();
        activeShelf = activePlayer.getShelf();

        //TODO to finish

        return 0;

    }
}
