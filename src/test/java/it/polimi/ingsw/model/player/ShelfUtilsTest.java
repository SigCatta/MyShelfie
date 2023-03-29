package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.cards.commonGoals.FourAnglesCGS;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

public class ShelfUtilsTest {
    @Test
    public void test(){
        ItemTile[][] matrix1 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
        };
        Shelf shelf = new Shelf(matrix1);
        assertFalse(ShelfUtils.checkMatrixWithDFS(shelf.getShelfGrid(), 8, 4));

        ItemTile[][] matrix2 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE)},
        };
        shelf = new Shelf(matrix2);
        assertTrue(ShelfUtils.checkMatrixWithDFS(shelf.getShelfGrid(), 4, 2));
    }

}
