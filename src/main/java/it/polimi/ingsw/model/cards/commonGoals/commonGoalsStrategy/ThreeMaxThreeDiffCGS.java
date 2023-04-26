package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
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
     * This function checks whether a shelfGrid has at least three columns, each consisting of 6 cells and no more than 3 different colors.
     * Different columns can have different combinations of Color cells.
     *
     * @param shelf the shelf to check
     * @return true if the matrix meets the specifics, false otherwise
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int countCol=0;
        // check each column
        for (int col = 0; col < shelfGrid[0].length; col++) {
            if (numOfNotNullCell(shelfGrid, col) < 6) {
                continue; // column has less than 6 cells
            }
            // count different colors in the column
            Set<Color> colorSet = new HashSet<>();
            for (ItemTile[] itemTiles : shelfGrid) {
                if (itemTiles[col] != null) {
                    colorSet.add(itemTiles[col].getColor());
                }
            }
            if (colorSet.size() <= 3) {
                countCol++;
            }
            if(countCol>=3)     return true;
        }
        return false;
    }
    /**
     * @param shelfGrid the Color matrix to check
     * @param col the column of the Color matrix to check
     * @return the number of non-null cells
     */
    private int numOfNotNullCell(ItemTile[][] shelfGrid, int col) {

        //TODO: USELESS
        int count = 0;
        for (ItemTile[] itemTiles : shelfGrid) {
            if (itemTiles[col] != null) {
                count++;
            }
        }
        return count;
    }


}




