package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Rule:
 * Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles
 * of one square can be different from those of the other square.
 */
public class TwoSquaresCGS extends CommonGoalStrategy {
    /**
     * Checks if a shelfGrid has at least 2 separate groups of 4 cells of the same color which form a 2x2 square.
     * @param shelf - the shelf to check
     * @return true if the matrix has at least 2 separate groups of 4 cells of the same Color that form a 2x2 square, false otherwise.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        Map<Color, Integer> colorMap = new HashMap<>();

        // Iterate through the matrix and look for groups of 4 cells that form a 2x2 square
        for(int i=0; i<shelfGrid.length-1; i++) {
            for(int j=0; j<shelfGrid[0].length-1; j++) {
                if(shelfGrid[i][j]!=null && shelfGrid[i+1][j]!=null &&
                        shelfGrid[i][j+1]!=null && shelfGrid[i+1][j+1]!=null) {
                    // Check if this is a potential group of 4 cells
                    Color color = shelfGrid[i][j].getColor();
                    if(color.equals(shelfGrid[i+1][j].getColor()) &&
                            color.equals(shelfGrid[i][j+1].getColor()) && color.equals(shelfGrid[i+1][j+1].getColor())) {
                        colorMap.put(color, 1 + colorMap.getOrDefault(color, 0));
                    }
                }
            }
        }
        // returns true if the number of squares with the same color is > 1
        return colorMap.values().stream().anyMatch(squareNum -> squareNum > 1 );
    }

    @Override
    public String getDescription() {
        return "Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2. Le tessere dei due gruppi devono essere dello stesso tipo.";
    }

}



