package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScaleCGSTest {
    @Test
    public void testHasFiveColumnsOfIncreasingHeight() {
        ScaleCGS cg = new ScaleCGS();
        ItemTile[][] matrix1 = {
                {null, null, null, null, null},
                {new ItemTile(Color.WHITE), null, null, null, null, },
                {new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null, null, },
                {new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, null, },
                {new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), null, },
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
        };
        Shelf shelf = new Shelf(matrix1);
        assertTrue(cg.isGoalAchieved(shelf));

        ItemTile[][] matrix2 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), null, null, null},
                {new ItemTile(Color.GREEN), null, null, null, null},
        };
        shelf = new Shelf(matrix2);
        assertTrue(cg.isGoalAchieved(shelf));

        ItemTile[][] matrix3 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), null, null, null},
                {new ItemTile(Color.GREEN), null, null, null, null},
        };
        shelf = new Shelf(matrix3);
        assertFalse(cg.isGoalAchieved(shelf));

        ItemTile[][] matrix4 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), null, null, null},
                {new ItemTile(Color.GREEN), null, null, null, null},
        };
        shelf = new Shelf(matrix4);
        assertFalse(cg.isGoalAchieved(shelf));
    }

}
