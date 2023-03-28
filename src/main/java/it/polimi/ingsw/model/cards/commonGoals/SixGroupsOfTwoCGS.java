package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.player.ShelfUtils;

/**
 * Rule:
 * Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo.
 * Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.
 */
public class SixGroupsOfTwoCGS extends CommonGoalStrategy {
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return ShelfUtils.checkMatrixWithDFS(shelf.generateColorMat(), 6, 2);
    }

    @Override
    public String getDescription() {
        return "Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo. Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.";
    }
}
