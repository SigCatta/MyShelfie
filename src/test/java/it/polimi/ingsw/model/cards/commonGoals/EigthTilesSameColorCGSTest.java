package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.EightTilesSameColorCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EigthTilesSameColorCGSTest {
    @Test
    public void isGoalAchievedTest() {
        EightTilesSameColorCGS cg = new EightTilesSameColorCGS();
        ItemTile[][] matrix = new ItemTile[][]{
                {null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK)},
                {null, new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN)},
                {null, null, null, null}
        };
        Shelf shelf = new Shelf(matrix);
        assertFalse(cg.isGoalAchieved(shelf));

        matrix = new ItemTile[][]{
                {null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.PINK), new ItemTile(Color.GREEN)},
                {null, null, null, null}
        };
        shelf = new Shelf(matrix);
        assertFalse(cg.isGoalAchieved(shelf));

        matrix = new ItemTile[][]{
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, null}
        };
        shelf = new Shelf(matrix);
        assertTrue(cg.isGoalAchieved(shelf));
    }
}
