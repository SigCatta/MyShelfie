package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.ShelfUtils;
import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourGroupsOfFourCGSTest {
    @Test
    public void testCheckMatrix() {
        FourGroupsOfFourCGS cg = new FourGroupsOfFourCGS();
        Color[][] matrix1 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
        };
        assertTrue(ShelfUtils.checkMatrixWithDFS(matrix1, 4, 4));

        Color[][] matrix2 = {
                {Color.PINK, Color.PINK, Color.BLUE},
                {Color.BLUE, Color.GREEN, Color.BLUE},
                {Color.GREEN, Color.GREEN, Color.GREEN},
        };
        assertFalse(ShelfUtils.checkMatrixWithDFS(matrix2, 4, 4));

        Color[][] matrix3 = {
                {Color.PINK, Color.PINK, null},
                {Color.BLUE, Color.GREEN, Color.BLUE},
                {Color.GREEN, Color.GREEN, Color.BLUE},
        };
        assertFalse(ShelfUtils.checkMatrixWithDFS(matrix3, 4, 4));
    }

}