package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.SixGroupsOfTwoCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SixGroupsOfTwoCGSTest {
    private final SixGroupsOfTwoCGS cg = new SixGroupsOfTwoCGS();

    @Test
    public void isGoalAchievedTest() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest2() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {new ItemTile(Color.YELLOW), null, null, null, null},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.GREEN), null, null},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), null},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), null},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest3() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest4() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {new ItemTile(Color.GREEN), null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest5() {
        ItemTile[][] mat = {
                {new ItemTile(Color.WHITE), null, null, null, null},
                {new ItemTile(Color.GREEN), null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest6() {
        ItemTile[][] mat = {
                {new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null, null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null, null, new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest7() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), null, new ItemTile(Color.PINK), null, null},
                {new ItemTile(Color.PINK), null, new ItemTile(Color.PINK), null, null},
                {new ItemTile(Color.PINK), null, new ItemTile(Color.PINK), null, new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest8() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), null, new ItemTile(Color.PINK), null, null},
                {new ItemTile(Color.PINK), null, new ItemTile(Color.PINK), null, new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), null, new ItemTile(Color.PINK), null, new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN), null, new ItemTile(Color.GREEN)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }
}

