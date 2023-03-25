package it.polimi.ingsw.model.cards.commonGoals;

import org.junit.Test;
import it.polimi.ingsw.model.tiles.Color;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TwoRowsWithFiveDiffCGTest {

    @Test
    public void testCheckColorMatrix() {
        TwoRowsWithFiveDiffCG cg = new TwoRowsWithFiveDiffCG();
        Color[][] matrix1 = {
                {Color.PINK, Color.YELLOW, Color.BLUE, Color.LIGHTBLUE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.YELLOW, Color.BLUE, Color.LIGHTBLUE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
        };
        assertTrue(cg.hasTwoRowsWithFiveDiff(matrix1));

        Color[][] matrix2 = {
                {Color.PINK, Color.YELLOW, Color.BLUE, Color.BLUE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, null, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.YELLOW, Color.BLUE, Color.YELLOW, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
        };
        assertFalse(cg.hasTwoRowsWithFiveDiff(matrix2));
    }
}
