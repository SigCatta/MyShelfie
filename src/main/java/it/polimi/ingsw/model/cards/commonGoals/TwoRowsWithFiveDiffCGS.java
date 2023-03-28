package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Due righe formate ciascuna da 5 diversi tipi di tessere.
 */
public class TwoRowsWithFiveDiffCGS extends CommonGoalStrategy {
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return hasTwoRowsWithFiveDiff(shelf.generateColorMat());
    }

    @Override
    public String getDescription() {
        return "Due righe formate ciascuna da 5 diversi tipi di tessere.";
    }

    /**
     * This method checks if a given Color matrix has at least two rows, each consisting of at least 5 different colors.
     *
     * @param colorMat the Color matrix to be checked
     * @return true if the condition is satisfied, false otherwise
     */
    public boolean hasTwoRowsWithFiveDiff(Color[][] colorMat) {
        int rows = colorMat.length;
        int columns = colorMat[0].length;

        for (int i = 0; i < rows; i++) {
            Set<Color> distinctColors = new HashSet<>();
            for (int j = 0; j < columns; j++) {
                if (colorMat[i][j] != null) {
                    distinctColors.add(colorMat[i][j]);
                }
            }
            if (distinctColors.size() >= 5) {   //first valid row found
                for (int k = i + 1; k < rows; k++) {
                    Set<Color> distinctColors2 = new HashSet<>();
                    for (int j = 0; j < columns; j++) {
                        if (colorMat[k][j] != null) {
                            distinctColors2.add(colorMat[k][j]);
                        }
                    }
                    if (distinctColors2.size() >= 5 && i != k) {    //second valid row found
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
