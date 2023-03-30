package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

/**
 * Rule:
 * Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2.
 * Le tessere dei due gruppi devono essere dello stesso tipo.
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

        // Iterate through the matrix and look for groups of 4 cells that form a 2x2 square
        for(int i=0; i<shelfGrid.length-1; i++) {
            for(int j=0; j<shelfGrid[0].length-1; j++) {
                if(shelfGrid[i][j]!=null && shelfGrid[i+1][j]!=null &&
                        shelfGrid[i][j+1]!=null && shelfGrid[i+1][j+1]!=null) {
                    // Check if this is a potential group of 4 cells
                    if(shelfGrid[i][j].getColor().equals(shelfGrid[i+1][j].getColor()) &&
                            shelfGrid[i][j].getColor().equals(shelfGrid[i][j+1].getColor()) &&
                            shelfGrid[i][j].getColor().equals(shelfGrid[i+1][j+1].getColor())) {
                        // Check if there is another group of 4 cells with the same color
                        for(int k=i+2; k<shelfGrid.length-1; k++) {
                            for(int l=0; l<shelfGrid[0].length-1; l++) {
                                if(shelfGrid[k][l]!=null && shelfGrid[k+1][l]!=null &&
                                        shelfGrid[k][l+1]!=null && shelfGrid[k+1][l+1]!=null) {
                                    if(shelfGrid[k][l].getColor().equals(shelfGrid[k+1][l].getColor()) &&
                                            shelfGrid[k][l].getColor().equals(shelfGrid[k][l+1].getColor()) &&
                                            shelfGrid[k][l].getColor().equals(shelfGrid[k+1][l+1].getColor()) &&
                                            shelfGrid[k][l].getColor().equals(shelfGrid[i][j].getColor())) {
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

    @Override
    public String getDescription() {
        return "Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2. Le tessere dei due gruppi devono essere dello stesso tipo.";
    }

}



