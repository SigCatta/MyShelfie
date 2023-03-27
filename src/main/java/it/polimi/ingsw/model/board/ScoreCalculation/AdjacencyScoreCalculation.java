package it.polimi.ingsw.model.board.ScoreCalculation;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

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
     * in the shelf, if three or more colors touch each other,
     * then the player should gain points
     * @return points
     */
    public int clalculateScore(){
        //these variables are set here, so they can change with the player turn
        activePlayer = GAME.getActivePlayer();
        activeShelf = activePlayer.getShelf();

        Color[][] shelfColorRepresentation = activeShelf.generateColorMat();



        //TODO to finish

        return 0;

    }
}
