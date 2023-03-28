package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Tre colonne formate ciascuna da 6 tessere di uno, due o tre tipi differenti.
 * Colonne diverse possono avere combinazioni diverse di tipi di tessere.
 */
public class ThreeMaxThreeDiffCGS extends CommonGoalStrategy {
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return hasThreeColumns(shelf.generateColorMat());
    }

    @Override
    public String getDescription() {
        return "Tre colonne formate ciascuna da 6 tessere di uno, due o tre tipi differenti. Colonne diverse possono avere combinazioni diverse di tipi di tessere.";
    }

    /**
     * This function checks whether a Color matrix has at least three columns, each consisting of 6 cells and no more than 3 different colors.
     * Different columns can have different combinations of Color cells.
     *
     * @param colorMat the Color matrix to check
     * @return true if the matrix meets the specifics, false otherwise
     */
    public boolean hasThreeColumns(Color[][] colorMat) {
        int countCol=0;
        // check each column
        for (int col = 0; col < colorMat[0].length; col++) {
            if (numOfNotNullCell(colorMat, col) < 6) {
                continue; // column has less than 6 cells
            }
            // count different colors in the column
            Set<Color> colorSet = new HashSet<>();
            for (int row = 0; row < colorMat.length; row++) {
                if (colorMat[row][col] != null) {
                    colorSet.add(colorMat[row][col]);
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
     * @param colorMat the Color matrix to check
     * @param col the column of the Color matrix to check
     * @return the number of non-null cells
     */
    private int numOfNotNullCell(Color[][] colorMat, int col) {
        int count = 0;
        for (int row = 0; row < colorMat.length; row++) {
            if (colorMat[row][col] != null ) {
                count++;
            }
        }
        return count;
    }


}




