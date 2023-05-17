package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.FourRowsOfFiveCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourRowsOfFiveCGSTest {
    private final FourRowsOfFiveCGS cg = new FourRowsOfFiveCGS();

    @Test
    public void isGoalAchievedTest1() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest2() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), null},
                {new ItemTile(Color.GREEN), null, new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), null},
                {new ItemTile(Color.GREEN), null, new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), null},
                {new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), null},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest3() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null, new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), null, new ItemTile(Color.WHITE)},
                {null, new ItemTile(Color.YELLOW), new ItemTile(Color.PINK), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), null},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest4() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.BLUE), null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }
}