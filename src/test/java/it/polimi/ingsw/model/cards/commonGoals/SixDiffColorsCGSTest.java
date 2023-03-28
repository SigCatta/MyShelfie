package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SixDiffColorsCGSTest {
    @Test
    public void testHasTwoColumnsOfSixDifferentColors() {
        SixDiffColorsCGS cg = new SixDiffColorsCGS();
        Color[][] matrix1 = {
                {Color.PINK, Color.BLUE, Color.LIGHTBLUE, Color.WHITE, Color.YELLOW, Color.GREEN},
                {Color.PINK, Color.BLUE, Color.LIGHTBLUE, Color.WHITE, Color.YELLOW, Color.GREEN},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.GREEN},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.GREEN},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.GREEN},
        };
        assertTrue(cg.hasTwoColumnsOfSixDifferentColors(matrix1));

        Color[][] matrix2 = {
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.GREEN},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.GREEN},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.GREEN},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.GREEN},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.GREEN},
        };
        assertFalse(cg.hasTwoColumnsOfSixDifferentColors(matrix2));
    }
}
