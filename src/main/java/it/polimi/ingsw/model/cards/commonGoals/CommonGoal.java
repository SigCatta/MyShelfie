package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Player;

import java.util.List;
import java.util.Stack;

public abstract class CommonGoal {
    protected Stack<Integer> pointsStack;
    protected List<Player> playersWhoCompleted;

    public abstract int calculateScore(Player player);
}
