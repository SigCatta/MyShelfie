package it.polimi.ingsw.model.cardCreation;

import it.polimi.ingsw.model.Player;

import java.util.List;
import java.util.Stack;

public abstract class CommonGoal {
    protected Stack<Integer> pointsStack;
    protected List<Player> playersWhoCompleted;

    public abstract int calculateScore(Player player);
}
