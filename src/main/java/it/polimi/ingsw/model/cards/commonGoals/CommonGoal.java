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
    protected Stack<Integer> pointsStack;
    protected List<Player> playersWhoCompleted;

    public int calculateScore(Player player) {
        if(isGoalAchieved(player.getShelf()))    return getPoints();
        else    return 0;
    }

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

    public abstract boolean isGoalAchieved(Shelf shelf);

    public int getPoints() {
        return pointsStack.get(0);
    }
}
