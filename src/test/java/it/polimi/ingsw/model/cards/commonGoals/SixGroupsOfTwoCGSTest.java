package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.ShelfUtils;
import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SixGroupsOfTwoCGSTest {

    @Test
    public void testCheckForGroups() {
        SixGroupsOfTwoCGS cg = new SixGroupsOfTwoCGS();
        // Test case 1: 6 groups of the same color
        Color[][] mat1 = {
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.LIGHTBLUE, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
        };
        assertTrue("Test case 1 failed", ShelfUtils.checkMatrixWithDFS(mat1, 6, 2));

        // Test case 2: 5 groups of the same color
        Color[][] mat2 = {
                {Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, null},
                {Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, null},
                {Color.LIGHTBLUE, Color.LIGHTBLUE, Color.GREEN, null, null},
                {Color.YELLOW, Color.BLUE, null, null, null},
                {Color.YELLOW, null, null, null, null},
                {null, null, null, null, null},
        };
        assertTrue("Test case 2 failed", ShelfUtils.checkMatrixWithDFS(mat2, 6, 2));

        // Test case 3: Less than 6 groups
        Color[][] mat3 = {
                {Color.PINK, Color.PINK, Color.PINK, Color.PINK, null},
                {Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
        };
        assertFalse("Test case 3 failed", ShelfUtils.checkMatrixWithDFS(mat3, 6, 2));
    }
}
