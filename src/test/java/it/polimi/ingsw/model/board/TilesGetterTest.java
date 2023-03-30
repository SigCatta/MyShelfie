package it.polimi.ingsw.model.board;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
import exceptions.TooManyTilesException;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TilesGetterTest {

/*
    @Test
    public void testInsertTilesTooManyTiles() throws NullItemTileException, FullColumnException {
        // Fill column 0 of the shelf grid except for the bottom cell
        for (int i = 0; i < testShelf.getROWS() - 1; i++) {
            ItemTile tile = new ItemTile(Color.PINK);
            testShelf.getShelfGrid()[i][0] = tile;
        }
        assertEquals(Color.PINK, testShelf.getTileAtLocation(new Point(0, 0)).getColor());

        // Insert two tiles in column 0 of the buffer
        List<ItemTile> tiles = Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE));
        try {
            testShelf.insertTiles(0);
        } catch (TooManyTilesException e) {
            assertEquals(TooManyTilesException.class, e.getClass());
        }
    }

 */
}
