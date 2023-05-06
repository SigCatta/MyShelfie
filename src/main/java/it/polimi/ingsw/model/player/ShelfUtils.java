package it.polimi.ingsw.model.player;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

public class ShelfUtils {
    /**
     * @param shelfGrid the matrix of colors to check, represented as a 2D array of Color objects
     * @param groupNum the number of separate group to find (at least)
     * @param groupSize the number of cells per group
     * @return true if the matrix has at least four groups with size >= 4, false otherwise
     */
    public static boolean checkMatrixWithDFS(ItemTile[][] shelfGrid, int groupNum, int groupSize) {
        int rows = shelfGrid.length;
        int columns = shelfGrid[0].length;

        // visit matrix and mark visited cells
        boolean[][] visited = new boolean[rows][columns];
        int groupCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (shelfGrid[i][j] != null && !visited[i][j]) {
                    // cell is not null and not visited
                    Color cellColor = shelfGrid[i][j].getColor();
                    int count = dfs(shelfGrid, visited, i, j, cellColor);
                    if (count >= groupSize) {
                        groupCount++;
                        if (groupCount >= groupNum) {
                            // at least groupNum groups found
                            return true;
                        }
                    }
                }
            }
        }

        // less than groupNum groups found
        return false;
    }

    /**
     * Recursive helper method for DFS algorithm to count adjacent cells with the same color.
     *
     * @param shelfGrid the matrix of colors to check
     * @param visited boolean matrix to mark visited cells
     * @param i current row index
     * @param j current column index
     * @param cellColor the color of the cell being visited
     * @return the count of adjacent cells with the same color
     */
    private static int dfs(ItemTile[][] shelfGrid, boolean[][] visited, int i, int j, Color cellColor) {
        int count = 1;
        visited[i][j] = true;

        // visit adjacent cells
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];

            if (x >= 0 && x < shelfGrid.length && y >= 0 && y < shelfGrid[0].length
                    && shelfGrid[x][y] != null && shelfGrid[x][y].getColor().equals(cellColor) && !visited[x][y]) {
                count += dfs(shelfGrid, visited, x, y, cellColor);
            }
        }

        return count;
    }
}
