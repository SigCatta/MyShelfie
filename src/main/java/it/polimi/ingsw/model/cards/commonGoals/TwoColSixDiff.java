package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Two columns each formed by 6 different types of tiles.
 */
public class TwoColSixDiff extends CommonGoalStrategy {
    /**
     * @param shelf The shelf to check.
     * @return True if the matrix has at least two columns formed by cells of 6 different colors, false otherwise.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int rows = shelfGrid.length;
        int cols = shelfGrid[0].length;
        int countCol = 0;

        for (int i = 0; i < rows; i++) {
            Set<Color> colorSet = new HashSet<>();
            for (int j = 0; j < cols; j++) {

                if(shelfGrid[i][j] != null) {
                    colorSet.add(shelfGrid[i][j].getColor());
                }
            }
            if(colorSet.size() >= 6)    countCol++;
            if(countCol > 1)    return true;
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Due colonne formate ciascuna da 6 diversi tipi di tessere.";
    }
}
