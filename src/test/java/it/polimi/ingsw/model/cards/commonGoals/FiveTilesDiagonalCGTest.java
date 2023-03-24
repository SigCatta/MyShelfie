package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FiveTilesDiagonalCGTest {
    @Test
    public void testDiagonal() {
        FiveTilesDiagonalCG cg = new FiveTilesDiagonalCG();
        Color[][] mat1 = new Color[][] {
                { null, null, null, null, null, null },
                { null, null, null, null, null, null },
                { null, null, Color.BLUE, null, null, null },
                { null, null, null, Color.BLUE, null, null },
                { null, null, null, null, Color.BLUE, null },
                { null, null, null, null, null, null }
        };
        assertFalse(cg.hasFiveDiagonalCellsOfSameCol(mat1));
        Color[][] mat2 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
        };
        assertFalse(cg.hasFiveDiagonalCellsOfSameCol(mat2));

        Color[][] mat3 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.YELLOW, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE, Color.PINK},
                {Color.PINK, Color.PINK, Color.YELLOW, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.YELLOW, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
        };
        assertTrue(cg.hasFiveDiagonalCellsOfSameCol(mat3));
    }
}
