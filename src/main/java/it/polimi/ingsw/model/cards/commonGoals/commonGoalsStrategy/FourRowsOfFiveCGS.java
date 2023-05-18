package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Four lines each formed by 5 tiles of maximum three different types. One
 * line can show the same or a different combination of another line.
 */
public class FourRowsOfFiveCGS extends CommonGoalStrategy {
    /**
     * @param shelf the shelf to be checked
     * @return true if a colorMat has at least four rows, each made up of at least 5 cells and with at most 3 different colors.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int validRows = 0;
        for (ItemTile[] row : shelfGrid) {
            if (hasLessThanFiveTiles(row)) continue;

            Set<Color> colors = new HashSet<>();
            for (ItemTile tile : row) {
                if (tile != null) colors.add(tile.getColor());
            }

            if (colors.size() <= 3) validRows++;
            if (validRows >= 4) return true;

        }

        return false;
    }

    /**
     * @param shelfRow the row to check
     * @return a boolean indicating if there are less than five tiles or not
     */
    private boolean hasLessThanFiveTiles(ItemTile[] shelfRow) {
        int count = 0;
        for (ItemTile tile : shelfRow) {
            if (tile != null) count++;
        }
        return count < 5;
    }
}
