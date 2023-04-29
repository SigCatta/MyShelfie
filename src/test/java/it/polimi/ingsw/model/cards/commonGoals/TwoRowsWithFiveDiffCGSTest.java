package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.TwoRowsWithFiveDiffCGS;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.TwoSquaresCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TwoRowsWithFiveDiffCGSTest {

    @Test
    public void isGoalAchievedTest() {
        TwoRowsWithFiveDiffCGS cg = new TwoRowsWithFiveDiffCGS();
        ItemTile[][] matrix1 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
        };
        Shelf shelf = new Shelf(matrix1);;
        assertTrue(cg.isGoalAchieved(shelf));

        ItemTile[][] matrix2 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), null, new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
        };
        shelf = new Shelf(matrix2);
        assertFalse(cg.isGoalAchieved(shelf));
    }

    @Test
    public void getDrawingForCLITest(){
        new TwoRowsWithFiveDiffCGS().getDrawingForCLI().forEach(System.out::println);
    }
    @Test
    public void getDescriptionTest(){
        System.out.println(new TwoRowsWithFiveDiffCGS().getDescription());
    }
}
