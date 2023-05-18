package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Two lines each formed by 5 different types of tiles. One line can show the
 * same or a different combination of the other line.
 */
public class TwoRowsWithFiveDiffCGS extends CommonGoalStrategy {
    /**
     * This method checks if a given shelfGrid has at least two rows, each consisting of at least 5 different colors.
     *
     * @param shelf the sheld to be checked
     * @return true if the condition is satisfied, false otherwise
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int columns = shelfGrid[0].length;
        int count = 0;

        for (ItemTile[] row : shelfGrid) {
            Set<Color> distinctColors = new HashSet<>();
            for (int j = 0; j < columns; j++) {
                if (row[j] == null) break;
                distinctColors.add(row[j].getColor());
            }
            if (distinctColors.size() >= 5) count++;
            if (count >= 2) return true;
        }
        return false;
    }
}