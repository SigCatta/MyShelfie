package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

/**
 * Rule:
 * Five tiles of the same type forming a diagonal.
 */
public class FiveTilesDiagonalCGS extends CommonGoalStrategy {
    /**
     * @param shelf the shelf to be checked
     * @return true if at least five cells of the same color are positioned diagonally, false otherwise
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int rows = shelfGrid.length;
        int cols = shelfGrid[0].length;

        for (int i = 0; i < rows - 4; i++) {
            for (int j = 0; j < cols - 4; j++) {
                if(shelfGrid[i][j] != null && shelfGrid[i+1][j+1] != null && shelfGrid[i+2][j+2] != null &&
                        shelfGrid[i+3][j+3] != null && shelfGrid[i+4][j+4] != null) {
                    if (shelfGrid[i][j].getColor().equals(shelfGrid[i+1][j+1].getColor())
                            && shelfGrid[i][j].getColor().equals(shelfGrid[i+2][j+2].getColor())
                            && shelfGrid[i][j].getColor().equals(shelfGrid[i+3][j+3].getColor())
                            && shelfGrid[i][j].getColor().equals(shelfGrid[i+4][j+4].getColor())) {
                        return true;
                    }
                }
            }
            for (int j = 4; j < cols; j++) {
                if(shelfGrid[i][j] != null && shelfGrid[i+1][j-1] != null && shelfGrid[i+2][j-2] != null &&
                        shelfGrid[i+3][j-3] != null && shelfGrid[i+4][j-4] != null) {
                    if ( shelfGrid[i][j].getColor().equals(shelfGrid[i+1][j-1].getColor())
                            && shelfGrid[i][j].getColor().equals(shelfGrid[i+2][j-2].getColor())
                            && shelfGrid[i][j].getColor().equals(shelfGrid[i+3][j-3].getColor())
                            && shelfGrid[i][j].getColor().equals(shelfGrid[i+4][j-4].getColor())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Cinque tessere dello stesso tipo che formano una diagonale.";
    }

}

