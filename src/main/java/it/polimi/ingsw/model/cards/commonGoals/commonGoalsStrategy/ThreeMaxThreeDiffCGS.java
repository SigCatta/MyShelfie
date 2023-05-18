package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Three columns each formed by 6 tiles of maximum three different types. One
 * column can show the same or a different combination of another column.
 */
public class ThreeMaxThreeDiffCGS extends CommonGoalStrategy {
    /**
     * Rule:
     * Three columns each formed by 6 tiles of maximum three different types. One
     * column can show the same or a different combination of another column.
     *
     * @param shelf the shelf to check
     * @return true if the matrix meets the specifics, false otherwise
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int count = 0;

        for (int col = 0; col < shelfGrid[0].length; col++) {
            Set<Color> colorSet = new HashSet<>();
            if (!shelf.isColumnFull(col)) continue;

            for (ItemTile[] itemTiles : shelfGrid) {
                if (itemTiles[col] != null) {
                    colorSet.add(itemTiles[col].getColor());
                }
            }
            if (colorSet.size() <= 3) {
                count++;
            }
            if (count >= 3) return true;
        }
        return false;
    }
}




