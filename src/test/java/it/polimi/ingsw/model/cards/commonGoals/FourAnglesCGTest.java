package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.ShelfBuffer;
import it.polimi.ingsw.model.player.ShelfInserter;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourAnglesCGTest {
    private FourAnglesCG cg;

    @Test
    public void checkColorTest() {
        cg = new FourAnglesCG();
        ItemTile tile = new ItemTile(Color.BLUE);
        assertTrue(cg.checkColor(Color.BLUE, tile));
        assertFalse(cg.checkColor(Color.PINK, tile));
        assertFalse(cg.checkColor(null, tile));
    }

    @Test
    public void hasEdgesWithSameColTest() {
        cg = new FourAnglesCG();
        Color[][] matrix1 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
        };
        assertFalse(cg.hasEdgesWithSameCol(matrix1));

        Color[][] matrix2 = {
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.PINK, Color.PINK, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.GREEN, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
                {Color.PINK, Color.GREEN, Color.BLUE, Color.WHITE, Color.WHITE, Color.PINK},
        };
        assertTrue(cg.hasEdgesWithSameCol(matrix2));
    }

}
