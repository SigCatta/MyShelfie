package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.List;
import java.util.Stack;

/**
 * a class that represent a particular kind of common goal card
 */
public abstract class CommonGoal {
    /**
     * Stack of points that can be obtained by achieving the goal.
     */
    protected Stack<Integer> pointsStack;
    /**
     * List of players who have completed the goal.
     */
    protected List<Player> playersWhoCompleted;

    /**
     * @param player the player whose score is being calculated
     * @return the point achieved by the player
     */
    public int calculateScore(Player player) {
        if(isGoalAchieved(player.getShelf()))    return getPoints();
        else    return 0;
    }

    /**
     * Generates a color matrix for the given shelf grid.
     * @param shelfGrid the grid of stacks representing the player's shelf
     * @param rows the number of rows in the shelf grid
     * @param cols the number of columns in the shelf grid
     * @return the color matrix representing the shelf grid
     */
    protected Color[][] generateColMat(List<Stack<ItemTile>> shelfGrid, int rows, int cols) {
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
     * @param shelf the shelf being checked for goal achievement
     * @return true if the goal has been achieved, false otherwise
     */
    public abstract boolean isGoalAchieved(Shelf shelf);

    /**
     * Gets the number of points awarded for achieving the goal.
     * @return the number of points awarded
     */
    public int getPoints() {
        return pointsStack.get(0);
    }
}




