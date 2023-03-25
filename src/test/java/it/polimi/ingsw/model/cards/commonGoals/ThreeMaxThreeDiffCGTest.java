package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ThreeMaxThreeDiffCGTest {
    @Test
    public void testHasThreeColumns() {
        ThreeMaxThreeDiffCG cg = new ThreeMaxThreeDiffCG();
        Color[][] matrix1 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
        };
        assertTrue(cg.hasThreeColumns(matrix1));

        Color[][] matrix2 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
        };
        assertFalse(cg.hasThreeColumns(matrix2));

        Color[][] matrix3 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.YELLOW, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.BLUE, Color.BLUE, null, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, null, Color.WHITE},
                {Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, null},
        };
        assertFalse(cg.hasThreeColumns(matrix3));

        Color[][] matrix4 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.WHITE, null, null},
        };
        assertTrue(cg.hasThreeColumns(matrix4));
    }
}
