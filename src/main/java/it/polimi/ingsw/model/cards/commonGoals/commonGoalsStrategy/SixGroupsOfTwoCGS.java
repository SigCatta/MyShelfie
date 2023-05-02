package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.player.ShelfUtils;

/**
 * Rule:
 * Six groups each containing at least 2 tiles of the same type.
 * The tiles of one group can be different from those of another group.
 */
public class SixGroupsOfTwoCGS extends CommonGoalStrategy {
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return ShelfUtils.checkMatrixWithDFS(shelf.getShelfGrid(), 6, 2);
    }
}
