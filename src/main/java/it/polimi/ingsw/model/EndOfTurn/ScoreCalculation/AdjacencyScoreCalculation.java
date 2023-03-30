package it.polimi.ingsw.model.EndOfTurn.ScoreCalculation;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

public class AdjacencyScoreCalculation {
    //TODO it might be useful to have an interface for the classes that calculate the score

    private final int POINTS_FOR_THREE = 2;
    private final int POINTS_FOR_FOUR  = 3;
    private final int POINTS_FOR_FIVE  = 5;
    private final int POINTS_FOR_SIX   = 8;

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
    public int calculateScore() {
        // These variables are set here, so they can change with the player turn
        activePlayer = GAME.getActivePlayer();
        activeShelf = activePlayer.getShelf();

        Color[][] shelfColorRepresentation = activeShelf.generateColorMat();

        int[] pointsForAdjacency = {0, 0, POINTS_FOR_THREE, POINTS_FOR_FOUR, POINTS_FOR_FIVE, POINTS_FOR_SIX};
        int points = 0;

        // Iterate over each tile in the shelf
        for (int i = 0; i < shelfColorRepresentation.length; i++) {
            for (int j = 0; j < shelfColorRepresentation[i].length; j++) {
                Color currentColor = shelfColorRepresentation[i][j];
                if (currentColor == null) {
                    continue; // Skip empty tiles
                }

                // Check if there are at least three tiles of the same color adjacent to the current tile
                int adjacentTiles = 0;
                if (i > 0 && shelfColorRepresentation[i - 1][j] == currentColor) {
                    adjacentTiles++;
                }
                if (i < shelfColorRepresentation.length - 1 && shelfColorRepresentation[i + 1][j] == currentColor) {
                    adjacentTiles++;
                }
                if (j > 0 && shelfColorRepresentation[i][j - 1] == currentColor) {
                    adjacentTiles++;
                }
                if (j < shelfColorRepresentation[i].length - 1 && shelfColorRepresentation[i][j + 1] == currentColor) {
                    adjacentTiles++;
                }

                if (adjacentTiles >= 3) {
                    points += pointsForAdjacency[adjacentTiles];
                }
            }
        }

        return points;
    }


}
