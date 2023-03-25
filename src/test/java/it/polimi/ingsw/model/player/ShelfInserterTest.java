package it.polimi.ingsw.model.player;

import exceptions.NullItemTileException;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShelfInserterTest {
    private ShelfInserter shelFInserter;

    @BeforeEach
    public void setUp(){
        shelFInserter = new ShelfInserter();
    }
    @Test
    public void insertTilesTest() throws NullItemTileException {
        Player player = new Player();
        shelFInserter.setActivePlayer(player);
        ShelfBuffer shelfBuffer = shelFInserter.getShelfBuffer();
        List<ItemTile> itemTiles = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        for (int i = 0; i < shelFInserter.getShelf().getROWS(); i++) {
            itemTiles.add(new ItemTile(Color.BLUE));
            colors.add(Color.BLUE);
        }
        shelfBuffer.setTiles(itemTiles);
        shelfBuffer.sortTiles(colors);
        assertTrue(shelFInserter.insertTiles(0));
        assertFalse(shelFInserter.insertTiles(0));

        for (int i = 0; i < shelFInserter.getShelf().getROWS(); i++) {
            assertEquals(itemTiles.get(i), shelFInserter.getShelfGridTilesAtColumn(0).get(i));
        }


    }

}
