package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShelfManagerTest {
    private ShelInserter shelInserter;

    @BeforeEach
    public void setUp(){
        shelInserter = new ShelInserter();
    }
    @Test
    public void insertTilesTest(){
        List<ItemTile> itemTiles = new ArrayList<>();
        for (int i = 0; i < shelInserter.getShelf().getROWS(); i++) {
            itemTiles.add(new ItemTile(Color.BLUE));
        }
        assertTrue(shelInserter.insertTiles(0, itemTiles));
        assertFalse(shelInserter.insertTiles(0, itemTiles));

        for (int i = 0; i < shelInserter.getShelf().getROWS(); i++) {
            assertEquals(itemTiles.get(i), shelInserter.getShelfGridTilesAtColumn(0).get(i));
        }


    }

}
