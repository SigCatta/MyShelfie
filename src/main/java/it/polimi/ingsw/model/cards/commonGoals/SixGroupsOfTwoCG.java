package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

/**
 * Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo.
 * Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.
 */
public class SixGroupsOfTwoCG extends CommonGoal{
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return checkForGroups(shelf.generateColorMat());
    }

    /**
     * Checks if there are at least 6 groups of adjacent cells with the same color in a matrix.
     * @param colorMat the matrix to be checked, represented as a 2D array of Color objects
     * @return true if there are at least 6 groups of adjacent cells with the same color, false otherwise
     */
    public boolean checkForGroups(Color[][] colorMat) {
        int numGroups = 0;
        Color lastColor = null;

        // Loop through the matrix
        for (int i = 0; i < colorMat.length; i++) {
            for (int j = 0; j < colorMat[i].length; j++) {
                // Check if the current cell is not null
                if (colorMat[i][j] != null) {
                    Color currentColor = colorMat[i][j];

                    // If the current color is the same as the last one,
                    // we are still inside the same group, so we skip to the next cell.
                    if (currentColor.equals(lastColor)) {
                        continue;
                    }

                    // Otherwise, we have just found a new group
                    numGroups++;
                    lastColor = currentColor;

                    // If we have found at least 6 groups, return true.
                    if (numGroups >= 6) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
