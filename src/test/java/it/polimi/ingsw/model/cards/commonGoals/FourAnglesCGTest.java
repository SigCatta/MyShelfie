package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.ShelInserter;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourAnglesCGTest {
    private ShelInserter shelInserter;
    private FourAnglesCG cg;

    @Test
    public void checkColorTest() {
        cg = new FourAnglesCG();
        shelInserter = new ShelInserter();
        ItemTile tile = new ItemTile(Color.BLUE);
        assertTrue(cg.checkColor(Color.BLUE, tile));
        assertFalse(cg.checkColor(Color.PINK, tile));
        assertFalse(cg.checkColor(null, tile));
    }

    @Test
    public void isGoalAchievedTest() {
        cg = new FourAnglesCG();
        shelInserter = new ShelInserter();
        shelInserter.insertTiles(0, Arrays.asList(new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.GREEN)));
        shelInserter.insertTiles(1, Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)));
        shelInserter.insertTiles(2, Arrays.asList(new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)));
        shelInserter.insertTiles(3, Arrays.asList(new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW)));
        shelInserter.insertTiles(4, Arrays.asList(new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW)));
        assertFalse(cg.isGoalAchieved(shelInserter.getShelf()));

        shelInserter = new ShelInserter();
        shelInserter.insertTiles(0, Arrays.asList(new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.PINK), new ItemTile(Color.PINK)));
        shelInserter.insertTiles(1, Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.PINK)));
        shelInserter.insertTiles(2, Arrays.asList(new ItemTile(Color.BLUE), new ItemTile(Color.BLUE)));
        shelInserter.insertTiles(3, Arrays.asList(new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW)));
        shelInserter.insertTiles(4, Arrays.asList(new ItemTile(Color.PINK), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN), new ItemTile(Color.PINK)));
        //assertTrue(cg.isGoalAchieved(shelInserter.getShelf()));
    }

}
