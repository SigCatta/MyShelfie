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
    public static Color[][] generateColMat(List<Stack<ItemTile>> shelfGrid, int rows, int cols) {
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
}
