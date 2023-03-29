package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.player.ShelfUtils;
import it.polimi.ingsw.model.tiles.Color;

/**
  * Rule:
  * Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo.
  * Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.
    */
public class FourGroupsOfFourCGS extends CommonGoalStrategy {
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return ShelfUtils.checkMatrixWithDFS(shelf.getShelfGrid(), 4, 4);
    }

    @Override
    public String getDescription() {
        return "Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo. Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.";
    }
}
