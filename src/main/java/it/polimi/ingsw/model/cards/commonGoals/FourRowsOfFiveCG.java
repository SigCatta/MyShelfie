package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * Quattro righe formate ciascuna da 5 tessere di uno, due o tre tipi
 * differenti. Righe diverse possono avere combinazioni diverse di tipi di tessere.
 */
public class FourRowsOfFiveCG extends CommonGoal{
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        Color[][] colorMat = generateColMat(shelf.getShelfGrid(), shelf.getROWS(), shelf.getCOLUMNS());
        return hasFourRowsOfFive(colorMat);
    }

    /**
     * @param colorMat the matrix to be checked, represented as a 2D array of Color objects
     * @return true if f a colorMat has at least four rows, each made up of at least 5 cells and with at most 3 different colors.
     */
    public boolean hasFourRowsOfFive(Color[][] colorMat) {
        for (int i = 0; i < colorMat.length; i++) {
            // Check if row has at least five cells
            if (numOfNotNullCell(colorMat, i) < 5) {
                continue;
            }

            // Count number of different colors in the row
            Set<Color> colors = new HashSet<>();
            for (int j = 0; j < colorMat[i].length; j++) {
                if (colorMat[i][j] != null) {
                    colors.add(colorMat[i][j]);
                }
            }

            // If row has more than three colors, move on to next row
            if (colors.size() > 3) {
                continue;
            }

            return true;
        }

        // If no rows meet criteria, return false
        return false;
    }

    /**
     * @param colorMat the Color matrix to check
     * @param row the row of the Color matrix to check
     * @return the number of non-null cells
     */
    private int numOfNotNullCell(Color[][] colorMat, int row) {
        int count = 0;
        for (int col = 0; col < colorMat[0].length; col++) {
            if (colorMat[row][col] != null ) {
                count++;
            }
        }
        return count;
    }
}
