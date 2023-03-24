package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * Due colonne formate ciascuna da 6 diversi tipi di tessere.
 */
public class SixDiffColorsCG extends CommonGoal{
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        Color[][] colorMat = generateColMat(shelf.getShelfGrid(), shelf.getROWS(), shelf.getCOLUMNS());
        return hasTwoColumnsOfSixDifferentColors(colorMat);
    }

    /**
     * @param colorMat The Color matrix to check.
     * @return True if the matrix has at least two columns formed by cells of 6 different colors, false otherwise.
     */
    public boolean hasTwoColumnsOfSixDifferentColors(Color[][] colorMat) {
        int rows = colorMat.length;
        int cols = colorMat[0].length;
        int countCol = 0;

        for (int i = 0; i < rows; i++) {
            Set<Color> colorSet = new HashSet<>();
            for (int j = 0; j < cols; j++) {

                if(colorMat[i][j] != null) {
                    colorSet.add(colorMat[i][j]);
                }
            }
            if(colorSet.size() >= 6)    countCol++;
            if(countCol > 1)    return true;
        }

        return false;
    }
}
