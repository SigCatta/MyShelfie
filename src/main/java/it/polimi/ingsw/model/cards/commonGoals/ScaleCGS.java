package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

/**
 * Rule:
 * Cinque colonne di altezza crescente o decrescente: a partire dalla prima colonna
 * a sinistra o a destra, ogni colonna successiva deve essere formata da una tessera in più.
 * Le tessere possono essere di qualsiasi tipo.
 */
public class ScaleCGS extends CommonGoalStrategy {
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        Color[][] colorMat = shelf.generateColorMat();

        return hasIncreasingColumns(colorMat, true) || hasIncreasingColumns(colorMat, false);
    }

    @Override
    public String getDescription() {
        return "Cinque colonne di altezza crescente o decrescente: a partire dalla prima colonna a sinistra o a destra, ogni colonna successiva deve essere formata da una tessera in più. \nLe tessere possono essere di qualsiasi tipo. ";
    }

    /**
     * @param colorMat The matrix to be analyzed
     * @param leftToRight A boolean indicating whether to start from the left or right
     * @return True if the matrix has at least five columns of increasing height, starting from
     *          the first column on the left or right
     */
    public boolean hasIncreasingColumns(Color[][] colorMat, boolean leftToRight) {
        int startCol = (leftToRight) ? 0 : colorMat[0].length - 1;
        int endCol = (leftToRight) ? colorMat[0].length - 1 : 0;
        int startRow = 0;
        int endRow = colorMat.length - 1;

        int nonNullCells = 0;
        int prevNonNullCells = 0;

        for (int col = startCol; leftToRight ? col <= endCol : col >= endCol;
             col += leftToRight ? 1 : -1) {
            for (int row = startRow; row <= endRow; row++) {
                if (colorMat[row][col] != null) {
                    nonNullCells++;
                }
            }
            if (prevNonNullCells > nonNullCells) {
                return false;
            }
            prevNonNullCells = nonNullCells;
            nonNullCells = 0;
        }
        return true;
    }
}



