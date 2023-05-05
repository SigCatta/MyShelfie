package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

import java.util.Stack;

public class CommonGoalCard {
    /**
     * Stack of points that can be obtained by achieving the goal.
     */
    protected Stack<Integer> pointsStack;

    private final CommonGoalStrategy commonGoalStrategy;

    public CommonGoalCard(CommonGoalStrategy commonGoalStrategy) {
        initStackPoints();
        this.commonGoalStrategy = commonGoalStrategy;
    }

    private void initStackPoints() {
        pointsStack = new Stack<>();
        for (int i = 1; i <= 4 ; i++) {
            pointsStack.push(i*2);
        }
    }

    /**
     * @param player the player whose score is being calculated
     * @return the point achieved by the player
     */
    public int calculateScore(Player player) {
        return isGoalAchieved(player.getShelf()) ? popPoints() : 0;
    }

    private boolean isGoalAchieved(Shelf shelf) {
        return commonGoalStrategy.isGoalAchieved(shelf);
    }

    /**
     * Gets the number of points awarded for achieving the goal.
     *
     * @return the number of points awarded
     */
    public int popPoints() {
        return pointsStack.pop();
    }

    public int peekPoints() {
        return pointsStack.peek();
    }

    public String getName() {
        return commonGoalStrategy.getCardName();
    }
}
