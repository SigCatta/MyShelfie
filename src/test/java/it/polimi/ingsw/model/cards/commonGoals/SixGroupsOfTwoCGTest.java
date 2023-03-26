package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SixGroupsOfTwoCGTest {

    @Test
    public void testCheckForGroups() {
        SixGroupsOfTwoCG cg = new SixGroupsOfTwoCG();
        // Test case 1: 6 groups of the same color
        Color[][] mat1 = {
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
        };
        assertTrue("Test case 1 failed", cg.checkForGroups(mat1));

        // Test case 2: 5 groups of the same color
        Color[][] mat2 = {
                {Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, null},
                {Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, null},
                {Color.YELLOW, Color.BLUE, Color.GREEN, null, null},
                {Color.YELLOW, Color.BLUE, null, null, null},
                {Color.YELLOW, null, null, null, null},
                {null, null, null, null, null},
        };
        assertTrue("Test case 2 failed", cg.checkForGroups(mat2));

        // Test case 3: Less than 6 groups
        Color[][] mat3 = {
                {Color.PINK, Color.PINK, Color.PINK, Color.PINK, null},
                {Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
        };
        assertFalse("Test case 3 failed", cg.checkForGroups(mat3));
    }
}
