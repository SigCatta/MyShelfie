package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.cards.commonGoals.FourAnglesCGS;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class ShelfUtilsTest {
    @Test
    public void testGenerateColMat() {
        CommonGoalStrategy commonGoalStrategy = new FourAnglesCGS();
        List<Stack<ItemTile>> shelfGrid = new ArrayList<>();
        Stack<ItemTile> stack1 = new Stack<>();
        stack1.push(new ItemTile(Color.PINK));
        stack1.push(new ItemTile(Color.BLUE));
        Stack<ItemTile> stack2 = new Stack<>();
        stack2.push(new ItemTile(Color.GREEN));
        stack2.push(new ItemTile(Color.YELLOW));
        shelfGrid.add(stack1);
        shelfGrid.add(stack2);
        Color[][] colMat = ShelfUtils.generateColorMat(shelfGrid, 2, 2);
        assertEquals(Color.PINK, colMat[0][0]);
        assertEquals(Color.BLUE, colMat[1][0]);
        assertEquals(Color.GREEN, colMat[0][1]);
        assertEquals(Color.YELLOW, colMat[1][1]);
    }
}
