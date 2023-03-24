package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourGroupsOfFourCGTest {
    @Test
    public void testCheckMatrix() {
        FourGroupsOfFourCG cg = new FourGroupsOfFourCG();
        Color[][] matrix1 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
        };
        assertTrue(cg.checkMatrix(matrix1));

        Color[][] matrix2 = {
                {Color.PINK, Color.PINK, Color.BLUE},
                {Color.BLUE, Color.GREEN, Color.BLUE},
                {Color.GREEN, Color.GREEN, Color.GREEN},
        };
        assertFalse(cg.checkMatrix(matrix2));

        Color[][] matrix3 = {
                {Color.PINK, Color.PINK, null},
                {Color.BLUE, Color.GREEN, Color.BLUE},
                {Color.GREEN, Color.GREEN, Color.BLUE},
        };
        assertFalse(cg.checkMatrix(matrix3));
    }

}