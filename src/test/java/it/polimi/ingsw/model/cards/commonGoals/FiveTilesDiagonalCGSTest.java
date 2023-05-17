package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.FiveTilesDiagonalCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FiveTilesDiagonalCGSTest {
   private final FiveTilesDiagonalCGS cg = new FiveTilesDiagonalCGS();
    @Test
    public void isGoalAchievedTest1() {
        ItemTile[][] mat = {
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest2() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest3() {
        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }
}
