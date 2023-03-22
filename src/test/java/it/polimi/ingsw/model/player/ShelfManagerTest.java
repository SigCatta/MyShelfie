package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShelfManagerTest {
    private ShelfManager shelfManager;

    @BeforeEach
    public void setUp(){
        shelfManager = new ShelfManager();
    }
    @Test
    public void insertTilesTest(){
        List<ItemTile> itemTiles = new ArrayList<>();
        for (int i = 0; i < shelfManager.getShelf().getROWS(); i++) {
            itemTiles.add(new ItemTile(Color.BLUE));
        }
        assertTrue(shelfManager.insertTiles(0, itemTiles));
        assertFalse(shelfManager.insertTiles(0, itemTiles));

        for (int i = 0; i < shelfManager.getShelf().getROWS(); i++) {
            assertEquals(itemTiles.get(i), shelfManager.getShelfGridTilesAtColumn(0).get(i));
        }


    }

}