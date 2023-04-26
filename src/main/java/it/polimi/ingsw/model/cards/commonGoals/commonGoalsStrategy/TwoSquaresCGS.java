package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

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

        int count = 0; //number of 2x2 blocks found

        for(int row = 0; row < shelfGrid.length-1; row++){
            for(int col = 0; col < shelfGrid[row].length-1; col++){
                if(check2x2Square(shelfGrid, row, col, shelfGrid[row][col].getColor())){
                    count++;
                    if(count >= 2) return true;
                }
            }
        }
        return false;
    }

    private boolean check2x2Square(ItemTile[][] shelf, int row, int col, Color currentColor){
        if(shelf[row+1][col].getColor() != currentColor)return false;
        if(shelf[row][col+1].getColor() != currentColor)return false;
        return shelf[row + 1][col + 1].getColor() == currentColor;
    }
}



