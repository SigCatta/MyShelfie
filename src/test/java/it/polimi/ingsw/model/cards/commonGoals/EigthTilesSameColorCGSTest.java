package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import static org.junit.Assert.*;

public class EigthTilesSameColorCGSTest {
    @Test
    public void isGoalAchieved() {
        EightTilesSameColorCGS cg1 = new EightTilesSameColorCGS();
        EightTilesSameColorCGS cg2 = new EightTilesSameColorCGS();
        EightTilesSameColorCGS cg3 = new EightTilesSameColorCGS();

        ItemTile[][] matrix1 = new ItemTile[][]{
                {null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK)},
                {null, new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.GREEN)},
                {null, null, null, null}
        };
        Shelf shelf1 = new Shelf(matrix1);

        ItemTile[][] matrix2 = new ItemTile[][]{
                {null, null, null, null},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {null, new ItemTile(Color.BLUE), new ItemTile(Color.PINK), new ItemTile(Color.GREEN)},
                {null, null, null, null}
        };
        Shelf shelf2 = new Shelf(matrix2);

        ItemTile[][] matrix3 = new ItemTile[][]{
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), null, null}
        };
        Shelf shelf3 = new Shelf(matrix3);

        boolean[] results = {cg1.isGoalAchieved(shelf1),cg2.isGoalAchieved(shelf2),cg3.isGoalAchieved(shelf3)};
        boolean[] expecteds = {false,false,true};
        assertArrayEquals(expecteds,results);

    }
}

