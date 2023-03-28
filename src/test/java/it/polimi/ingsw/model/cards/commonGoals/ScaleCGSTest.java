package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScaleCGSTest {
    @Test
    public void testHasFiveColumnsOfIncreasingHeight() {
        ScaleCGS cg = new ScaleCGS();
        Color[][] matrix1 = {
                {Color.PINK, Color.YELLOW, Color.BLUE, Color.LIGHTBLUE, Color.WHITE},
                {null, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {null, null, Color.BLUE, Color.WHITE, Color.WHITE},
                {null, null, null, Color.WHITE, Color.WHITE},
                {null, null, null, null, Color.WHITE},
                {null, null, null, null, null},
        };
        assertTrue(cg.hasIncreasingColumns(matrix1, true));
        assertFalse(cg.hasIncreasingColumns(matrix1, false));

        Color[][] matrix2 = {
                {Color.PINK, Color.YELLOW, Color.BLUE, Color.LIGHTBLUE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, null},
                {Color.PINK, Color.PINK, Color.BLUE, null, null},
                {Color.PINK, Color.YELLOW, null, null, null},
                {Color.GREEN, null, null, null, null},
        };
        assertTrue(cg.hasIncreasingColumns(matrix2, false));
        assertFalse(cg.hasIncreasingColumns(matrix2, true));
    }

}
