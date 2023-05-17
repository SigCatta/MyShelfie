package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.ScaleCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScaleCGSTest {
    private final ScaleCGS cg = new ScaleCGS();

    @Test
    public void isGoalAchievedTest1() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {new ItemTile(Color.WHITE), null, null, null, null,},
                {new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null, null,},
                {new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null,},
                {new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null,},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest2() {
        ItemTile[][] mat = {
                {null, null, null, new ItemTile(Color.BLUE), null},
                {null, null, null, new ItemTile(Color.BLUE), null,},
                {new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null, null,},
                {new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null,},
                {new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null,},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest3() {
        ItemTile[][] mat = {
                {new ItemTile(Color.BLUE), null, null, null, null},
                {new ItemTile(Color.WHITE), null, null, null, null,},
                {new ItemTile(Color.WHITE), null, new ItemTile(Color.BLUE), null, null,},
                {new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null,},
                {new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null,},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest4() {
        ItemTile[][] mat = {
                {null, null, null, null, new ItemTile(Color.WHITE)},
                {null, null, null, null, new ItemTile(Color.WHITE),},
                {null, null, null, new ItemTile(Color.WHITE), new ItemTile(Color.WHITE),},
                {null, null, new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE),},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE),},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest5() {
        ItemTile[][] mat = {
                {null, null, null, null, null},
                {new ItemTile(Color.WHITE), null, null, null, null,},
                {new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null, null,},
                {new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null,},
                {new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null,},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), null},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest6() {
        ItemTile[][] mat = {
                {null, null, null, null, new ItemTile(Color.WHITE)},
                {null, null, null, null, new ItemTile(Color.WHITE),},
                {null, null, null, new ItemTile(Color.WHITE), new ItemTile(Color.WHITE),},
                {null, null, null, new ItemTile(Color.WHITE), new ItemTile(Color.WHITE),},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE),},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)}
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }
}
