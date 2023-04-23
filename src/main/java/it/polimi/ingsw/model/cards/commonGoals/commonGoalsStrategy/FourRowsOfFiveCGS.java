package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
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
        for (int i = 0; i < shelfGrid.length; i++) {
            // Check if row has at least five cells
            if (numOfNotNullCell(shelfGrid, i) < 5) {
                continue;
            }

            // Count number of different colors in the row
            Set<Color> colors = new HashSet<>();
            for (int j = 0; j < shelfGrid[i].length; j++) {
                if (shelfGrid[i][j] != null) {
                    colors.add(shelfGrid[i][j].getColor());
                }
            }

            // If row has more than three colors, move on to next row
            if (colors.size() <= 3) {
                validRows++;
            }
            if(validRows>=4)    return true;

        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Quattro righe formate ciascuna da 5 tessere di uno, due o tre tipi differenti. Righe diverse possono avere combinazioni diverse di tipi di tessere.";
    }

    /**
     * @param shelfGrid the Color matrix to check
     * @param row the row of the Color matrix to check
     * @return the number of non-null cells
     */
    private int numOfNotNullCell(ItemTile[][] shelfGrid, int row) {
        int count = 0;
        for (int col = 0; col < shelfGrid[0].length; col++) {
            if (shelfGrid[row][col] != null ) {
                count++;
            }
        }
        return count;
    }
}
