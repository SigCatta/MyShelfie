package it.polimi.ingsw.model.cards.commonGoals;


import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoSquaresCGTest {
    @Test
    public void testHasTwoGroupsOfFour() {
        TwoSquaresCG cg = new TwoSquaresCG();
        Color[][] mat1 = {
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
        };
        assertFalse(cg.hasTwoGroupsOfFour(mat1));

        Color[][] mat2 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.PINK, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.BLUE, null},
        };
        assertFalse(cg.hasTwoGroupsOfFour(mat2));

        Color[][] mat3 = {
                {Color.BLUE, Color.BLUE, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.BLUE, Color.BLUE, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.BLUE, null},
        };
        assertTrue(cg.hasTwoGroupsOfFour(mat3));
    }
}
