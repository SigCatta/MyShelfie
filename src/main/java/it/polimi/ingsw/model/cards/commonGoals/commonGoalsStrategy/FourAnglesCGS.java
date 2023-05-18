package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

/**
 * Rule:
 * Four tiles of the same type in the four corners of the bookshelf.
 */
public class FourAnglesCGS extends CommonGoalStrategy {

    /**
     * Checks if the goal of having all tiles on the corners with the same color is achieved.
     *
     * @param shelf the shelf to check
     * @return true if the goal of having all tiles on the corners with the same color is achieved.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int rows = shelfGrid.length;
        int cols = shelfGrid[0].length;
        try {
            Color color = shelfGrid[0][0].getColor();
            if (shelfGrid[0][cols - 1].getColor() != color) return false;
            if (shelfGrid[rows - 1][cols - 1].getColor() != color) return false;
            if (shelfGrid[rows - 1][0].getColor() != color) return false;
        } catch (NullPointerException e) {
            return false;
        }

        return true;
    }
}
