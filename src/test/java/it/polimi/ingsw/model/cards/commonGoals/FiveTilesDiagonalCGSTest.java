package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FiveTilesDiagonalCGSTest {
    @Test
    public void testDiagonal() {
        FiveTilesDiagonalCGS cg = new FiveTilesDiagonalCGS();
        ItemTile[][] mat1 = {
                { null, null, null, null, null, null },
                { null, null, null, null, null, null },
                { null, null, new ItemTile(Color.BLUE), null, null, null },
                { null, null, null, new ItemTile(Color.BLUE), null, null },
                { null, null, null, null, new ItemTile(Color.BLUE), null },
                { null, null, null, null, null, null }
        };
        Shelf shelf = new Shelf(mat1);
        assertFalse(cg.isGoalAchieved(shelf));

        ItemTile[][] mat2 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        shelf = new Shelf(mat2);
        assertFalse(cg.isGoalAchieved(shelf));

        ItemTile[][] mat3 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        shelf = new Shelf(mat3);
        assertTrue(cg.isGoalAchieved(shelf));
    }
}
