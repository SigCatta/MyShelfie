package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FiveTilesDiagonalCGSTest {

    @BeforeAll
    public void setUp() {

    }

    @Test
    public void testDiagonal1() {
        FiveTilesDiagonalCGS cg = new FiveTilesDiagonalCGS();
        ItemTile[][] mat1 = {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, new ItemTile(Color.BLUE), null, null, null},
                {null, null, null, new ItemTile(Color.BLUE), null, null},
                {null, null, null, null, new ItemTile(Color.BLUE), null},
                {null, null, null, null, null, null}
        };
        Shelf shelf = new Shelf(mat1);
        assertFalse(cg.isGoalAchieved(shelf));
    }

    @Test
    public void testDiagonal2(){
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
    public void testDiagonal3(){
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
    public void testDiagonal4(){
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
    public void testDiagonal5(){

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

}
