package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.tiles.Color;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EigthTilesSameColorCGSTest {
    @Test
    public void testHasAtLeastEightCellsOfSameColor() {
        EigthTilesSameColorCGS cg = new EigthTilesSameColorCGS();
        Color[][] matrix = new Color[][]{
                {null, null, null, null},
                {Color.PINK, Color.PINK, Color.PINK, Color.PINK},
                {null, Color.BLUE, Color.PINK, Color.GREEN},
                {null, null, null, null}
        };
        assertFalse(cg.hasAtLeastEightCellsOfSameColor(matrix));

        matrix = new Color[][]{
                {null, null, null, null},
                {Color.PINK, Color.PINK, Color.PINK, Color.BLUE},
                {null, Color.BLUE, Color.PINK, Color.GREEN},
                {null, null, null, null}
        };
        assertFalse(cg.hasAtLeastEightCellsOfSameColor(matrix));

        matrix = new Color[][]{
                {Color.PINK, Color.PINK, Color.PINK, Color.BLUE},
                {Color.PINK, Color.PINK, Color.PINK, Color.BLUE},
                {Color.PINK, Color.PINK, Color.PINK, Color.BLUE},
                {Color.PINK, Color.PINK, null, null}
        };
        assertTrue(cg.hasAtLeastEightCellsOfSameColor(matrix));
    }
}
