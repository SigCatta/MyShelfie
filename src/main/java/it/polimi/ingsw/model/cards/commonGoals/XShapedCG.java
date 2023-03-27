package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

/**
 * Cinque tessere dello stesso tipo che formano una X.
 */
public class XShapedCG extends CommonGoal{
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return hasFiveCellsFormingX(shelf.generateColorMat());
    }

    /**
     * @param colorMat the matrix to be checked
     * @return true if there are at least five cells of the same color that form an X, false otherwise
     */
    public boolean hasFiveCellsFormingX(Color[][] colorMat) {
        for (int i = 1; i < colorMat.length - 1; i++) {
            for (int j = 1; j < colorMat[i].length - 1; j++) {
                if (colorMat[i][j] == null || colorMat[i - 1][j - 1] == null || colorMat[i + 1][j + 1] == null
                        || colorMat[i - 1][j + 1] == null || colorMat[i + 1][j - 1] == null)
                    continue;

                // Check for main diagonal
                if (colorMat[i - 1][j - 1].equals(colorMat[i][j])
                        && colorMat[i + 1][j + 1].equals(colorMat[i][j]) ) {
                    // Check for secondary diagonal
                    if (colorMat[i - 1][j + 1].equals(colorMat[i][j])
                            && colorMat[i + 1][j - 1].equals(colorMat[i][j])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
