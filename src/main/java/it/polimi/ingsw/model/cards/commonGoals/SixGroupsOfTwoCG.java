package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.*;

public class SixGroupsOfTwoCG extends CommonGoal{
    /*
    Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo.
    Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        Color[][] colorMat = generateColMat(shelf.getShelfGrid(), shelf.getROWS(), shelf.getCOLUMNS());
        return checkForGroups(colorMat);
    }

    private static boolean checkForGroups(Color[][] colorMat) {
        // Initialize variables to keep track of the groups found
        int numGroups = 0;
        Color lastColor = null;

        // Loop through the matrix
        for (int i = 0; i < colorMat.length; i++) {
            for (int j = 0; j < colorMat[i].length; j++) {
                // Check if the current cell is not null
                if (colorMat[i][j] != null) {
                    Color currentColor = colorMat[i][j];

                    // If the current color is the same as the last one,
                    // we are still inside the same group, so continue.
                    if (currentColor.equals(lastColor)) {
                        continue;
                    }

                    // Otherwise, we have just found a new group,
                    // so increment the counter and store the current color.
                    numGroups++;
                    lastColor = currentColor;

                    // If we have found at least 6 groups, return true.
                    if (numGroups >= 6) {
                        return true;
                    }
                }
            }
        }

        // If we haven't found enough groups, return false.
        return false;
    }
}
