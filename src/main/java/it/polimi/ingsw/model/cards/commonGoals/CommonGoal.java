package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

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
        return isGoalAchieved(player.getShelf()) ? getPoints() : 0;
    }

    /**
     * @param shelf the shelf being checked for goal achievement
     * @return true if the goal has been achieved, false otherwise
     */
    public abstract boolean isGoalAchieved(Shelf shelf);

    /**
     * Gets the number of points awarded for achieving the goal.
     *
     * @return the number of points awarded
     */
    public int getPoints() {
        return pointsStack.pop();
    }
}




