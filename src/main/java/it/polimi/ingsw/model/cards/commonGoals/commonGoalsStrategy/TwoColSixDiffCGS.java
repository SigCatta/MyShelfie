package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Two columns each formed by 6 different types of tiles.
 */
public class TwoColSixDiffCGS extends CommonGoalStrategy {
    /**
     * @param shelf The shelf to check.
     * @return True if the matrix has at least two columns formed by cells of 6 different colors, false otherwise.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        if (shelfGrid[0] == null) return false; // should never happen

        int cols = shelfGrid[0].length;
        int countCol = 0;

        for (int i = 0; i < cols; i++) {
            Set<Color> colorSet = new HashSet<>();
            for (ItemTile[] row : shelfGrid) {
                if (row[i] == null) break;
                colorSet.add(row[i].getColor());
            }
            if (colorSet.size() >= 6) countCol++;
            if (countCol >= 2) return true;
        }

        return false;
    }
}
