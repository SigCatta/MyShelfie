package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles
 * of one square can be different from those of the other square.
 */
public class TwoSquaresCGS extends CommonGoalStrategy {
    private Set<String> visitedPositions;
    private Set<Color> visitedBoxColor;
    private ItemTile[][] shelfGrid;

    /**
     * Checks if a shelfGrid has at least 2 separate groups of 4 cells of the same color which form a 2x2 square.
     * @param shelf - the shelf to check
     * @return true if the matrix has at least 2 separate groups of 4 cells of the same Color that form a 2x2 square, false otherwise.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        this.shelfGrid = shelf.getShelfGrid();

        visitedPositions = new HashSet<>();
        visitedBoxColor = new HashSet<>();

        for(int col = 0; col < shelfGrid[0].length - 1; col++){
            for(int row = shelfGrid.length - 1; row > 0; row--){

                if(shelfGrid[row][col] == null) break;
                if(visitedPositions.contains(row + "," + col))continue;

                Color currentColor = shelfGrid[row][col].getColor();

                if(!check2x2Square(row, col, currentColor)) continue;

                if(visitedBoxColor.contains(currentColor)) return true;
                visitedBoxColor.add(currentColor);
            }
        }
        return false;
    }

    private boolean check2x2Square(int row, int col, Color currentColor){
        int[][] positionsToCheck = {{row-1, col+1}, {row-1, col}, {row, col+1}};

        for(int[] el : positionsToCheck){
            if(shelfGrid[el[0]][el[1]] == null) return false;
            if(shelfGrid[el[0]][el[1]].getColor() != currentColor) return false;
            if(visitedPositions.contains(el[0] + "," + el[1])) return false;
        }

        for(int[] el : positionsToCheck){
            visitedPositions.add(el[0] + "," + el[1]);
        }

        return true;
    }
}



