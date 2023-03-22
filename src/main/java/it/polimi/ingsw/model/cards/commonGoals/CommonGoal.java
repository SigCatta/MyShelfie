package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

import java.util.List;
import java.util.Stack;

public abstract class CommonGoal {
    protected Stack<Integer> pointsStack;
    protected List<Player> playersWhoCompleted;

    public int calculateScore(Player player) {
        if(isGoalAchieved(player.getShelf()))    return getPoints();
        else    return 0;
    }

    public abstract boolean isGoalAchieved(Shelf shelf);

    public int getPoints() {
        return pointsStack.get(0);
    }
}
