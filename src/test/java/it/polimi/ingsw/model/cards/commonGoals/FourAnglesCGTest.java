package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.player.ShelfManager;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourAnglesCGTest {
    private ShelfManager shelfManager;
    private FourAnglesCG cg;

    @Test
    public void checkColorTest() {
        cg = new FourAnglesCG();
        shelfManager = new ShelfManager();
        ItemTile tile = new ItemTile(Color.BLUE);
        assertTrue(cg.checkColor(Color.BLUE, tile));
        assertFalse(cg.checkColor(Color.PINK, tile));
        assertFalse(cg.checkColor(null, tile));
    }

    @Test
    public void isGoalAchievedTest() {
        cg = new FourAnglesCG();
        shelfManager = new ShelfManager();
        shelfManager.insertTiles(0, Arrays.asList(new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.GREEN)));
        shelfManager.insertTiles(1, Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)));
        shelfManager.insertTiles(2, Arrays.asList(new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)));
        shelfManager.insertTiles(3, Arrays.asList(new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW)));
        shelfManager.insertTiles(4, Arrays.asList(new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW)));
        assertFalse(cg.isGoalAchieved(shelfManager.getShelf()));

        shelfManager = new ShelfManager();
        shelfManager.insertTiles(0, Arrays.asList(new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.PINK)));
        shelfManager.insertTiles(1, Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)));
        shelfManager.insertTiles(2, Arrays.asList(new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)));
        shelfManager.insertTiles(3, Arrays.asList(new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW)));
        shelfManager.insertTiles(4, Arrays.asList(new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.PINK)));
        //assertTrue(cg.isGoalAchieved(shelfManager.getShelf()));
    }

}
