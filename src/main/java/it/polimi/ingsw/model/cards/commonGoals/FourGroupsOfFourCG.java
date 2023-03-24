package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

/**
  * Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo.
  * Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.
    */
public class FourGroupsOfFourCG extends CommonGoal{
    /**
     * Checks if the given matrix of colors has at least four groups of colors with size
     * greater or equal to four.
     *
     * @param colorMat the matrix of colors to check, represented as a 2D array of Color objects
     * @return true if the matrix has at least four groups with size >= 4, false otherwise
     */
    public boolean checkMatrix(Color[][] colorMat) {
        int rows = colorMat.length;
        int columns = colorMat[0].length;

        // visit matrix and mark visited cells
        boolean[][] visited = new boolean[rows][columns];
        int groupCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (colorMat[i][j] != null && !visited[i][j]) {
                    // cell is not null and not visited
                    Color cellColor = colorMat[i][j];
                    int count = dfs(colorMat, visited, i, j, cellColor);
                    if (count >= 4) {
                        groupCount++;
                        if (groupCount >= 4) {
                            // at least four groups found
                            return true;
                        }
                    }
                }
            }
        }

        // less than four groups found
        return false;
    }

    /**
     * Recursive helper method for DFS algorithm to count adjacent cells with the same color.
     *
     * @param colorMat the matrix of colors to check
     * @param visited boolean matrix to mark visited cells
     * @param i current row index
     * @param j current column index
     * @param cellColor the color of the cell being visited
     * @return the count of adjacent cells with the same color
     */
    private int dfs(Color[][] colorMat, boolean[][] visited, int i, int j, Color cellColor) {
        int count = 1;
        visited[i][j] = true;

        // visit adjacent cells
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];

            if (x >= 0 && x < colorMat.length && y >= 0 && y < colorMat[0].length
                    && colorMat[x][y] != null && colorMat[x][y].equals(cellColor) && !visited[x][y]) {
                count += dfs(colorMat, visited, x, y, cellColor);
            }
        }

        return count;
    }

    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        Color[][] colorMat = generateColMat(shelf.getShelfGrid(), shelf.getROWS(), shelf.getCOLUMNS());
        return checkMatrix(colorMat);
    }
}
