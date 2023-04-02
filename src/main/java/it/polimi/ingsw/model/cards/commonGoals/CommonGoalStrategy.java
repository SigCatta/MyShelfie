package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;

/**
 * a class that represent a particular kind of common goal card Strategy.
 * It contains the logic to calculate weather a common goal has been achieved by a player
 */
public abstract class CommonGoalStrategy {
    /**
     * @param shelf the shelf being checked for goal achievement
     * @return true if the goal has been achieved, false otherwise
     */
    public abstract boolean isGoalAchieved(Shelf shelf);

    public abstract String getDescription();
}




