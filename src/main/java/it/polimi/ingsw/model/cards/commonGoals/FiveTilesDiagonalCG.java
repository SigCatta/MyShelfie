package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

/**
 * Cinque tessere dello stesso tipo che formano una diagonale.
 */
public class FiveTilesDiagonalCG extends CommonGoal{
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return hasFiveDiagonalCellsOfSameCol(shelf.generateColorMat());
    }

    /**
     * @param colorMat the Color matrix to be checked
     * @return true if at least five cells of the same color are positioned diagonally, false otherwise
     */
    public boolean hasFiveDiagonalCellsOfSameCol(Color[][] colorMat) {
        int rows = colorMat.length;
        int cols = colorMat[0].length;

        for (int i = 0; i < rows - 4; i++) {
            for (int j = 0; j < cols - 4; j++) {
                if(colorMat[i][j] != null && colorMat[i+1][j+1] != null && colorMat[i+2][j+2] != null &&
                    colorMat[i+3][j+3] != null && colorMat[i+4][j+4] != null) {
                    if (colorMat[i][j].equals(colorMat[i+1][j+1])
                            && colorMat[i][j].equals(colorMat[i+2][j+2])
                            && colorMat[i][j].equals(colorMat[i+3][j+3])
                            && colorMat[i][j].equals(colorMat[i+4][j+4])) {
                        return true;
                    }
                }
            }
            for (int j = 4; j < cols; j++) {
                if(colorMat[i][j] != null && colorMat[i+1][j-1] != null && colorMat[i+2][j-2] != null &&
                        colorMat[i+3][j-3] != null && colorMat[i+4][j-4] != null) {
                    if ( colorMat[i][j] == colorMat[i+1][j-1]
                            && colorMat[i][j] == colorMat[i+2][j-2]
                            && colorMat[i][j] == colorMat[i+3][j-3]
                            && colorMat[i][j] == colorMat[i+4][j-4]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

