package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

import java.util.List;
import java.util.Stack;

public class CommonGoalCard {
    /**
     * Stack of points that can be obtained by achieving the goal.
     */
    protected Stack<Integer> pointsStack;
    /**
     * List of players who have completed the goal.
     */
    protected List<Player> playersWhoCompleted;

    private CommonGoalStrategy commonGoalStrategy;

    public CommonGoalCard(CommonGoalStrategy commonGoalStrategy) {
        this.commonGoalStrategy = commonGoalStrategy;
    }


    /**
     * @param player the player whose score is being calculated
     * @return the point achieved by the player
     */
    public int calculateScore(Player player) {
        return isGoalAchieved(player.getShelf()) ? getPoints() : 0;
    }

    private boolean isGoalAchieved(Shelf shelf) {
        return commonGoalStrategy.isGoalAchieved(shelf);
    }

    /**
     * Gets the number of points awarded for achieving the goal.
     * @return the number of points awarded
     */
    public int getPoints() {
        return pointsStack.pop();
    }

    public String getDescription() {
        return commonGoalStrategy.getDescription();
    }
}
