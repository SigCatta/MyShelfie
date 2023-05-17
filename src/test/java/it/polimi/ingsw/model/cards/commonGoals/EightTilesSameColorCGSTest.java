package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.EightTilesSameColorCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EightTilesSameColorCGSTest {
    private final EightTilesSameColorCGS cg = new EightTilesSameColorCGS();

    @Test
    public void isGoalAchievedTest1() {
        ItemTile[][] mat = new ItemTile[][]{
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), null},
                {new ItemTile(Color.WHITE), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest2() {
        ItemTile[][] mat = new ItemTile[][]{
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(mat)));
    }

    @Test
    public void isGoalAchievedTest3() {
        ItemTile[][] mat = new ItemTile[][]{
                {null, null, null, null, null},
                {null, null, null, null, null},
                {new ItemTile(Color.WHITE), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null},
                {new ItemTile(Color.WHITE), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null},
                {new ItemTile(Color.WHITE), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null},
                {new ItemTile(Color.WHITE), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), null},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(mat)));
    }
}
