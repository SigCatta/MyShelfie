package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class XShapedCGSTest {
    @Test
    public void testhasFiveCellsFormingX() {
        XShapedCGS cg = new XShapedCGS();
        Color[][] matrix1 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.LIGHTBLUE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.YELLOW, Color.BLUE, Color.LIGHTBLUE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
        };
        assertFalse(cg.hasFiveCellsFormingX(matrix1));

        Color[][] matrix2 = {
                {Color.LIGHTBLUE, Color.YELLOW, Color.LIGHTBLUE, Color.LIGHTBLUE, Color.WHITE},
                {Color.GREEN, Color.LIGHTBLUE, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.LIGHTBLUE, Color.GREEN, Color.LIGHTBLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.PINK, Color.YELLOW, Color.BLUE, Color.LIGHTBLUE, Color.WHITE},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE},
        };
        assertTrue(cg.hasFiveCellsFormingX(matrix2));
    }
}
