package it.polimi.ingsw.model.cards.commonGoals;


import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.TwoSquaresCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoSquaresCGSTest {
    @Test
    public void multipleShelvesTest() {
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
                {new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.PINK)},
        };
        shelf = new Shelf(mat3);
        assertTrue(cg.isGoalAchieved(shelf));
    }

    @Test
    public void allNullTest(){
        TwoSquaresCGS cg = new TwoSquaresCGS();
        ItemTile[][] shelf = new ItemTile[6][5];
        assertFalse(cg.isGoalAchieved(new Shelf(shelf)));
    }
    @Test
    public void TwoFullColumnsTest(){
        TwoSquaresCGS cg = new TwoSquaresCGS();
        ItemTile[][] shelf = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), null, null, null},
        };
        assertTrue(cg.isGoalAchieved(new Shelf(shelf)));
    }

    @Test
    public void twoGroupsDifferentColorsTest(){
        TwoSquaresCGS cg = new TwoSquaresCGS();
        ItemTile[][] shelf = {
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), null, null, null},
                {new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), null, new ItemTile(Color.YELLOW), new ItemTile(Color.YELLOW)},
                {new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), null, new ItemTile(Color.YELLOW), new ItemTile(Color.YELLOW)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(shelf)));
    }

    @Test
    public void centralBlockTest(){
        TwoSquaresCGS cg = new TwoSquaresCGS();
        ItemTile[][] shelf = {
                {null,                      new ItemTile(Color.BLUE),   null, new ItemTile(Color.BLUE),     new ItemTile(Color.BLUE)},
                {null,                      new ItemTile(Color.BLUE),   null, new ItemTile(Color.BLUE),     new ItemTile(Color.BLUE)},
                {null,                      new ItemTile(Color.YELLOW), null, new ItemTile(Color.BLUE),     new ItemTile(Color.BLUE)},
                {new ItemTile(Color.BLUE),  new ItemTile(Color.YELLOW), null, new ItemTile(Color.YELLOW),   new ItemTile(Color.LIGHTBLUE)},
                {new ItemTile(Color.BLUE),  new ItemTile(Color.YELLOW), null, new ItemTile(Color.YELLOW),   new ItemTile(Color.YELLOW)},
                {new ItemTile(Color.BLUE),  new ItemTile(Color.YELLOW), null, new ItemTile(Color.YELLOW),   new ItemTile(Color.YELLOW)},
        };
        assertFalse(cg.isGoalAchieved(new Shelf(shelf)));
    }
}
