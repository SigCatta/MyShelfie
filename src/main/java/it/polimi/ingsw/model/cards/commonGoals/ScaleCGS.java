package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

/**
 * Rule:
 * Five columns of increasing or decreasing height. Starting from the first column on
 * the left or on the right, each next column must be made of exactly one more tile.
 * Tiles can be of any type.
 */
public class ScaleCGS extends CommonGoalStrategy {
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();

        return hasIncreasingColumns(shelfGrid, true) || hasIncreasingColumns(shelfGrid, false);
    }

    @Override
    public String getDescription() {
        return "Cinque colonne di altezza crescente o decrescente: a partire dalla prima colonna a sinistra o a destra, ogni colonna successiva deve essere formata da una tessera in più. \nLe tessere possono essere di qualsiasi tipo. ";
    }

    /**
     * @param shelfGrid The matrix to be analyzed
     * @param leftToRight A boolean indicating whether to start from the left or right
     * @return True if the matrix has at least five columns of increasing height, starting from
     *          the first column on the left or right
     */
    public boolean hasIncreasingColumns(ItemTile[][] shelfGrid, boolean leftToRight) {
        int startCol = (leftToRight) ? 0 : shelfGrid[0].length - 1;
        int endCol = (leftToRight) ? shelfGrid[0].length - 1 : 0;
        int startRow = 0;
        int endRow = shelfGrid.length - 1;

        int nonNullCells = 0;
        int prevNonNullCells = 0;

        for (int col = startCol; leftToRight ? col <= endCol : col >= endCol;
             col += leftToRight ? 1 : -1) {
            for (int row = startRow; row <= endRow; row++) {
                if (shelfGrid[row][col] != null) {
                    nonNullCells++;
                }
            }
            if (prevNonNullCells >= nonNullCells) {
                return false;
            }
            prevNonNullCells = nonNullCells;
            nonNullCells = 0;
        }
        return true;
    }
}



