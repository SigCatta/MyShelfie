package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.TwoSquaresCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoSquaresCGSTest {
    private final TwoSquaresCGS cg = new TwoSquaresCGS();

    @Test
    public void isGoalAchievedTest1() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest2() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest3() {
        ItemTile[][] mat = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest4() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null, new ItemTile(Color.BLUE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null, new ItemTile(Color.BLUE), new ItemTile(Color.WHITE)},
        };

        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest5() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
        };

        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest6() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), null, null, null, null},
                {new ItemTile(Color.PINK), null, null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
        };

        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest7() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
        };

        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest8() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), null, new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
        };

        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest9() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {null, null, null, null, new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
        };

        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest10() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), null,                      new ItemTile(Color.PINK), null,               new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), null,                    new ItemTile(Color.PINK), null,                  new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK),new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), null                     , new ItemTile(Color.PINK), null,                   new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null,                       new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
        };

        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

}
