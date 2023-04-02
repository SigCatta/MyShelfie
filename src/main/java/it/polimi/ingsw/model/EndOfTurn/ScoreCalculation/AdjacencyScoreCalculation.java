package it.polimi.ingsw.model.EndOfTurn.ScoreCalculation;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;

public class AdjacencyScoreCalculation {

    /** calculate the points gained from the adjacency of tiles
     * in the shelf, if three or more colors touch each other,
            * then the player should gain points
     * @return points
     */
    public static int calculateScore(Player activePlayer) {

        final int POINTS_FOR_THREE = 2;
        final int POINTS_FOR_FOUR = 3;
        final int POINTS_FOR_FIVE = 5;
        final int POINTS_FOR_SIX = 8;

        Shelf activeShelf = activePlayer.getShelf();
        ItemTile[][] shelf = activeShelf.getShelfGrid();

        int[] pointsForAdjacency = {0, 0, 0, POINTS_FOR_THREE, POINTS_FOR_FOUR, POINTS_FOR_FIVE, POINTS_FOR_SIX};

        int points = 0;

        List<Integer> tilesInClusterCount = findClusters(shelf);

        for (Integer tilesCount : tilesInClusterCount) {
            points += pointsForAdjacency[min(tilesCount, pointsForAdjacency.length - 1)];
        }

        return points;
    }


    /** @param shelf the shelf of the player
     * @return a list containing the list of points in each cluster
     */
    private static List<Integer> findClusters(ItemTile[][] shelf) {
        List<Integer> tilesForEachCluster = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int row = 0; row < shelf.length; row++) {
            for (int col = 0; col < shelf[0].length; col++) {


                ItemTile currentTile = shelf[row][col];

                Color currentColor;
                if(currentTile == null) currentColor = null;
                else{
                    currentColor = currentTile.getColor();
                }

                String key = row + "," + col;

                if (!seen.contains(key)) {
                    List<Integer> TilesInCurrentCluster = dfs(shelf, row, col, currentColor, seen);
                    tilesForEachCluster.add(TilesInCurrentCluster.stream().reduce(0, Integer::sum));
                }

            }
        }

        return tilesForEachCluster;
    }

    /**
     *
     * @param shelf the shelf representation with colors instead of tiles
     * @param row current row
     * @param col current column
     * @param currentColor color of the current cluster
     * @param seen positions already visited
     * @return a list with the size of tiles in a certain group
     */
    private static List<Integer> dfs(ItemTile[][] shelf, int row, int col, Color currentColor, Set<String> seen) {

        if (row < 0 || row >= shelf.length || col < 0 || col >= shelf[0].length || currentColor == null) {
            return new ArrayList<>();
        }
        if(shelf[row][col] != null && shelf[row][col].getColor() != currentColor){
            return new ArrayList<>();
        }

        String key = row + "," + col;
        if (seen.contains(key)) {
            return new ArrayList<>();
        }
        seen.add(key);

        if(shelf[row][col] == null) return new ArrayList<>();

        List<Integer> partialCounts = new ArrayList<>();
        partialCounts.add(1);

        int[][] neighbors = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};
        for (int[] neighbor : neighbors) {
            partialCounts.addAll(dfs(shelf, neighbor[0], neighbor[1], currentColor, seen));
        }

        return partialCounts;
    }


}