package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.ShelfUtils;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SixGroupsOfTwoCGSTest {

    @Test
    public void testCheckForGroups() {
        SixGroupsOfTwoCGS cg = new SixGroupsOfTwoCGS();
        // Test case 1: 6 groups of the same color
        ItemTile[][] mat1 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
        };
        assertTrue("Test case 1 failed", ShelfUtils.checkMatrixWithDFS(mat1, 6, 2));

        // Test case 2: 5 groups of the same color
        ItemTile[][] mat2 = {
                {new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), null},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), null},
                {new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.GREEN), null, null},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.YELLOW), null, null, null, null},
                {null, null, null, null, null},
        };
        assertTrue("Test case 2 failed", ShelfUtils.checkMatrixWithDFS(mat2, 6, 2));

        // Test case 3: Less than 6 groups
        ItemTile[][] mat3 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), null},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
        };
        assertFalse("Test case 3 failed", ShelfUtils.checkMatrixWithDFS(mat3, 6, 2));
    }
}
