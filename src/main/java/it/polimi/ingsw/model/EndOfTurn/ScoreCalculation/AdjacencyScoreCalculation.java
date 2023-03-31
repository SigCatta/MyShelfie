package it.polimi.ingsw.model.EndOfTurn.ScoreCalculation;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;

public class AdjacencyScoreCalculation {

    /**
     * calculate the points gained from the adjacency of tiles
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
        Color[][] shelfColor = activeShelf.generateColorMat();

        int[] pointsForAdjacency = {0, 0, 0, POINTS_FOR_THREE, POINTS_FOR_FOUR, POINTS_FOR_FIVE, POINTS_FOR_SIX};

        int points = 0;

        List<List<int[]>> clusters = findClusters(shelfColor);

        for (List<int[]> cluster : clusters) {

            int tilesInClusterCount = min(cluster.size(), pointsForAdjacency.length - 1);
            points += pointsForAdjacency[tilesInClusterCount];

        }

        return points;
    }


    /**
     * @param colorShelf the shelf of the player
     * @return a list containing the list of points in each cluster
     */
    private static List<List<int[]>> findClusters(Color[][] colorShelf) {
        List<List<int[]>> clusters = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int row = 0; row < colorShelf.length; row++) {
            for (int col = 0; col < colorShelf[0].length; col++) {

                Color currentColor = colorShelf[row][col];
                String key = row + "," + col;
                if (!seen.contains(key)) {
                    List<int[]> cluster = dfs(colorShelf, row, col, currentColor, seen);
                    if (!cluster.isEmpty()) {
                        clusters.add(cluster);
                    }
                }
            }
        }

        return clusters;
    }

    /**
     *
     * @param colorShelf the shelf representation with colors instead of tiles
     * @param row current row
     * @param col current column
     * @param currentColor color of the current cluster
     * @param seen positions already visited
     * @return a list containing the positions where the tiles are adjacent and same color
     */
    private static List<int[]> dfs(Color[][] colorShelf, int row, int col, Color currentColor, Set<String> seen) {
        if (row < 0 || row >= colorShelf.length || col < 0 || col >= colorShelf[0].length
                || colorShelf[row][col] != currentColor || currentColor == null) {
            return new ArrayList<>();
        }
        String key = row + "," + col;
        if (seen.contains(key)) {
            return new ArrayList<>();
        }
        seen.add(key);
        List<int[]> colorCluster = new ArrayList<>();
        colorCluster.add(new int[]{row, col});
        int[][] neighbors = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};
        for (int[] neighbor : neighbors) {
            colorCluster.addAll(dfs(colorShelf, neighbor[0], neighbor[1], currentColor, seen));
        }
        return colorCluster;
    }


}
