package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.List;
import java.util.Stack;

public class ShelfUtils {
    /**
     * Generates a color matrix for the given shelf grid.
     * @param shelfGrid the grid of stacks representing the player's shelf
     * @param rows the number of rows in the shelf grid
     * @param cols the number of columns in the shelf grid
     * @return the color matrix representing the shelf grid
     */
    public static Color[][] generateColorMat(List<Stack<ItemTile>> shelfGrid, int rows, int cols) {
        Color[][] colorMat = new Color[rows][cols];
        for (int i = 0; i < rows; i++) {
            colorMat[i] = new Color[cols];
            for (int j = 0; j < cols; j++) {
                Stack<ItemTile> stack = shelfGrid.get(j);
                if(stack.size() >= i+1) {
                    colorMat[i][j] = stack.get(i).getColor();
                }
            }
        }
        return colorMat;
    }

    /**
     * @param colorMat the matrix of colors to check, represented as a 2D array of Color objects
     * @param groupNum the number of separate group to find (at least)
     * @param groupSize the number of cells per group
     * @return true if the matrix has at least four groups with size >= 4, false otherwise
     */
    public static boolean checkMatrixWithDFS(Color[][] colorMat, int groupNum, int groupSize) {
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
     * @param colorMat the matrix of colors to check
     * @param visited boolean matrix to mark visited cells
     * @param i current row index
     * @param j current column index
     * @param cellColor the color of the cell being visited
     * @return the count of adjacent cells with the same color
     */
    private static int dfs(Color[][] colorMat, boolean[][] visited, int i, int j, Color cellColor) {
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
}
