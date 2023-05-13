package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.FourGroupsOfFourCGS;
import it.polimi.ingsw.model.player.ShelfUtils;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourGroupsOfFourCGSTest {
    @Test
    public void testCheckMatrixWithDFSTest() {
        FourGroupsOfFourCGS cg = new FourGroupsOfFourCGS();
        ItemTile[][] matrix1 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        assertTrue(ShelfUtils.checkMatrixWithDFS(matrix1, 4, 4));

        ItemTile[][] matrix2 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.GREEN)},
        };
        assertFalse(ShelfUtils.checkMatrixWithDFS(matrix2, 4, 4));

        ItemTile[][] matrix3 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE)},
        };
        assertFalse(ShelfUtils.checkMatrixWithDFS(matrix3, 4, 4));
    }
}