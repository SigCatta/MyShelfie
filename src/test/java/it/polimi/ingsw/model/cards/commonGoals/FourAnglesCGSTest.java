package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.FourAnglesCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourAnglesCGSTest {
    private FourAnglesCGS cg;

    @Test
    public void checkColorTest() {
        cg = new FourAnglesCGS();
        ItemTile tile = new ItemTile(Color.BLUE);
        assertTrue(cg.checkColor(Color.BLUE, tile));
        assertFalse(cg.checkColor(Color.PINK, tile));
        assertFalse(cg.checkColor(null, tile));
    }

    @Test
    public void isGoalAchievedTest() {
        cg = new FourAnglesCGS();
        ItemTile[][] matrix1 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        Shelf shelf = new Shelf(matrix1);
        assertFalse(cg.isGoalAchieved(shelf));

        ItemTile[][] matrix2 = {
                {new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
        };
        shelf = new Shelf(matrix2);
        assertTrue(cg.isGoalAchieved(shelf));
    }

    @Test
    public void getDrawingForCLITest(){
        new FourAnglesCGS().getDrawingForCLI().forEach(System.out::println);
    }
    @Test
    public void getDescriptionTest(){
        System.out.println(new FourAnglesCGS().getDescription());
    }
}
