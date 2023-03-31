package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Due righe formate ciascuna da 5 diversi tipi di tessere.
 */
public class TwoRowsWithFiveDiffCGS extends CommonGoalStrategy {
    /**
     * This method checks if a given shelfGrid has at least two rows, each consisting of at least 5 different colors.
     *
     * @param shelf the sheld to be checked
     * @return true if the condition is satisfied, false otherwise
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int rows = shelfGrid.length;
        int columns = shelfGrid[0].length;

        for (int i = 0; i < rows; i++) {
            Set<Color> distinctColors = new HashSet<>();
            for (int j = 0; j < columns; j++) {
                if (shelfGrid[i][j] != null) {
                    distinctColors.add(shelfGrid[i][j].getColor());
                }
            }
            if (distinctColors.size() >= 5) {   //first valid row found
                for (int k = i + 1; k < rows; k++) {
                    Set<Color> distinctColors2 = new HashSet<>();
                    for (int j = 0; j < columns; j++) {
                        if (shelfGrid[k][j] != null) {
                            distinctColors2.add(shelfGrid[k][j].getColor());
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

    @Override
    public String getDescription() {
        return "Due righe formate ciascuna da 5 diversi tipi di tessere.";
    }
}