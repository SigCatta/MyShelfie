package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourRowsOfFiveTestCG {
    @Test
    public void hasFourRowsOfFiveTest() {
        FourRowsOfFiveCG cg = new FourRowsOfFiveCG();
        Color[][] matrix1 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
        };
        assertTrue(cg.hasFourRowsOfFive(matrix1));

        Color[][] matrix2 = {
                {Color.PINK, null, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, null, Color.BLUE, Color.WHITE, null},
                {Color.GREEN, null, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, null, Color.BLUE, null, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, null},
        };
        assertFalse(cg.hasFourRowsOfFive(matrix2));

        Color[][] matrix3 = {
                {Color.PINK, Color.LIGHTBLUE, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.YELLOW, Color.LIGHTBLUE, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.BLUE, Color.BLUE, null, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, null, Color.WHITE},
                {null, Color.YELLOW, Color.PINK, Color.LIGHTBLUE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, null},
        };
        assertFalse(cg.hasFourRowsOfFive(matrix3));

        Color[][] matrix4 = {
                {Color.PINK, Color.LIGHTBLUE, Color.BLUE, Color.YELLOW, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.WHITE, null, null},
        };
        assertTrue(cg.hasFourRowsOfFive(matrix4));
    }
}