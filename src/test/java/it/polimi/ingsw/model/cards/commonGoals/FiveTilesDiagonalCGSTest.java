package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiveTilesDiagonalCGSTest {

/*    @Before
    public void setUp() {

    }*/



    @Test
    public void isGoalAchieved() {

        FiveTilesDiagonalCGS cg1 = new FiveTilesDiagonalCGS();
        FiveTilesDiagonalCGS cg2 = new FiveTilesDiagonalCGS();
        FiveTilesDiagonalCGS cg3 = new FiveTilesDiagonalCGS();
        FiveTilesDiagonalCGS cg4 = new FiveTilesDiagonalCGS();

        ItemTile[][] shelfGrid1 = new ItemTile[6][5];
        shelfGrid1[0][0] = new ItemTile(Color.BLUE);
        shelfGrid1[1][1] = new ItemTile(Color.BLUE);
        shelfGrid1[2][2] = new ItemTile(Color.BLUE);
        shelfGrid1[3][3] = new ItemTile(Color.BLUE);
        shelfGrid1[4][4] = new ItemTile(Color.BLUE);

        Shelf shelf1 = new Shelf(shelfGrid1);

        ItemTile[][] shelfGrid2 = new ItemTile[6][5];
        shelfGrid2[0][4] = new ItemTile(Color.BLUE);
        shelfGrid2[1][3] = new ItemTile(Color.BLUE);
        shelfGrid2[2][2] = new ItemTile(Color.BLUE);
        shelfGrid2[3][1] = new ItemTile(Color.BLUE);
        shelfGrid2[4][0] = new ItemTile(Color.BLUE);

        Shelf shelf2 = new Shelf(shelfGrid2);

        ItemTile[][] shelfGrid3 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        Shelf shelf3 = new Shelf(shelfGrid3);

        ItemTile[][] shelfGrid4 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        Shelf shelf4 = new Shelf(shelfGrid4);

        boolean result1 = cg1.isGoalAchieved(shelf1);
        boolean result2 = cg2.isGoalAchieved(shelf2);
        boolean result3 = cg3.isGoalAchieved(shelf3);
        boolean result4 = cg4.isGoalAchieved(shelf4);

        boolean[] results = {result1,result2,result3,result4};
        boolean[] expecteds={true,true,false,true};
        assertArrayEquals(expecteds,results);
    }

/*    @Test
    public void isGoalAchieved_2(){
        FiveTilesDiagonalCGS cg = new FiveTilesDiagonalCGS();

        ItemTile[][] mat2 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        Shelf shelf = new Shelf(mat2);
        assertFalse(cg.isGoalAchieved(shelf));
    }
    @Test
    public void isGoalAchieved_3(){
        FiveTilesDiagonalCGS cg = new FiveTilesDiagonalCGS();
        ItemTile[][] mat3 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
                {new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.PINK)},
        };
        Shelf shelf = new Shelf(mat3);
        assertTrue(cg.isGoalAchieved(shelf));
    }

    @Test
    public void isGoalAchieved_4(){
        FiveTilesDiagonalCGS cg = new FiveTilesDiagonalCGS();
        ItemTile[][] shelfGrid = new ItemTile[6][5];
        shelfGrid[0][0] = new ItemTile(Color.BLUE);
        shelfGrid[1][1] = new ItemTile(Color.BLUE);
        shelfGrid[2][2] = new ItemTile(Color.BLUE);
        shelfGrid[3][3] = new ItemTile(Color.BLUE);
        shelfGrid[4][4] = new ItemTile(Color.BLUE);

        Shelf shelf = new Shelf(shelfGrid);
        assertTrue(cg.isGoalAchieved(shelf));
    }

    @Test
    public void isGoalAchieved_5(){

        FiveTilesDiagonalCGS cgs = new FiveTilesDiagonalCGS();

        ItemTile[][] shelfGrid = new ItemTile[6][5];
        shelfGrid[0][4] = new ItemTile(Color.BLUE);
        shelfGrid[1][3] = new ItemTile(Color.BLUE);
        shelfGrid[2][2] = new ItemTile(Color.BLUE);
        shelfGrid[3][1] = new ItemTile(Color.BLUE);
        shelfGrid[4][0] = new ItemTile(Color.BLUE);

        Shelf shelf = new Shelf(shelfGrid);
        assertTrue(cgs.isGoalAchieved(shelf));
    }

 */

}

