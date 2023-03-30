package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

/**
 * Rule:
 * Cinque tessere dello stesso tipo che formano una X.
 */
public class XShapedCGS extends CommonGoalStrategy {
    /**
     * @param shelf the shelf to be checked
     * @return true if there are at least five cells of the same color that form an X, false otherwise
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        for (int i = 1; i < shelfGrid.length - 1; i++) {
            for (int j = 1; j < shelfGrid[i].length - 1; j++) {
                if (shelfGrid[i][j] == null || shelfGrid[i - 1][j - 1] == null || shelfGrid[i + 1][j + 1] == null
                        || shelfGrid[i - 1][j + 1] == null || shelfGrid[i + 1][j - 1] == null)
                    continue;

                // Check for main diagonal
                if (shelfGrid[i - 1][j - 1].getColor().equals(shelfGrid[i][j].getColor())
                        && shelfGrid[i + 1][j + 1].getColor().equals(shelfGrid[i][j].getColor()) ) {
                    // Check for secondary diagonal
                    if (shelfGrid[i - 1][j + 1].getColor().equals(shelfGrid[i][j].getColor())
                            && shelfGrid[i + 1][j - 1].getColor().equals(shelfGrid[i][j].getColor())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Cinque tessere dello stesso tipo che formano una X.";
    }

}
