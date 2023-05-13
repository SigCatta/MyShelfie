package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.TwoSquaresCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoSquaresCGSTest {
    @Test
    public void isGoalAchievedTest() {
        TwoSquaresCGS cg = new TwoSquaresCGS();
        ItemTile[][] mat1 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
        };
        Shelf shelf = new Shelf(mat1);
        assertFalse(cg.isGoalAchieved(shelf));

        ItemTile[][] mat2 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null},
        };
        shelf = new Shelf(mat2);
        assertFalse(cg.isGoalAchieved(shelf));

        ItemTile[][] mat3 = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null},
        };
        shelf = new Shelf(mat3);
        assertTrue(cg.isGoalAchieved(shelf));
    }
}
