package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.FourGroupsOfFourCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourGroupsOfFourCGSTest {
    private final FourGroupsOfFourCGS cg = new FourGroupsOfFourCGS();

    @Test
    public void testCheckMatrixWithDFSTest1() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void testCheckMatrixWithDFSTest2() {
        ItemTile[][] mat = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void testCheckMatrixWithDFSTest3() {
        ItemTile[][] mat = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), null, new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void testCheckMatrixWithDFSTest4() {
        ItemTile[][] mat = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.BLUE), null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.BLUE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void testCheckMatrixWithDFSTest5() {
        ItemTile[][] mat = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.BLUE), null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.BLUE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void testCheckMatrixWithDFSTest6() {
        ItemTile[][] mat = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), null, new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }
}