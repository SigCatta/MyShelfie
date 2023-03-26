package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

/**
 * Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2.
 * Le tessere dei due gruppi devono essere dello stesso tipo.
 */
public class TwoSquaresCG extends CommonGoal{
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return hasTwoGroupsOfFour(shelf.generateColorMat());
    }

    /**
     * Checks if a given matrix has at least 2 separate groups of 4 cells of the same color which form a 2x2 square.
     * @param colorMat - the matrix to check
     * @return true if the matrix has at least 2 separate groups of 4 cells of the same Color that form a 2x2 square, false otherwise.
     */
    public boolean hasTwoGroupsOfFour(Color[][] colorMat) {
        // Iterate through the matrix and look for groups of 4 cells that form a 2x2 square
        for(int i=0; i<colorMat.length-1; i++) {
            for(int j=0; j<colorMat[0].length-1; j++) {
                if(colorMat[i][j]!=null && colorMat[i+1][j]!=null &&
                        colorMat[i][j+1]!=null && colorMat[i+1][j+1]!=null) {
                    // Check if this is a potential group of 4 cells
                    if(colorMat[i][j].equals(colorMat[i+1][j]) &&
                            colorMat[i][j].equals(colorMat[i][j+1]) &&
                            colorMat[i][j].equals(colorMat[i+1][j+1])) {
                        // Check if there is another group of 4 cells with the same color
                        for(int k=i+2; k<colorMat.length-1; k++) {
                            for(int l=0; l<colorMat[0].length-1; l++) {
                                if(colorMat[k][l]!=null && colorMat[k+1][l]!=null &&
                                       colorMat[k][l+1]!=null && colorMat[k+1][l+1]!=null) {
                                    if(colorMat[k][l].equals(colorMat[k+1][l]) &&
                                            colorMat[k][l].equals(colorMat[k][l+1]) &&
                                            colorMat[k][l].equals(colorMat[k+1][l+1]) &&
                                            colorMat[k][l].equals(colorMat[i][j])) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // If we get here, there are no two groups of 4 cells with the same color that form a 2x2 square
        return false;
    }
}



