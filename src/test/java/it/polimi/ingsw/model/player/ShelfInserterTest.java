package it.polimi.ingsw.model.player;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
import exceptions.NullPlayersException;
import exceptions.TooManyTilesException;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShelfInserterTest {
    private ShelfInserter shelFInserter;
/*
    @BeforeEach
    public void setUp(){
        shelFInserter = new ShelfInserter();
    }


    @Test
    public void insertTilesTest() throws NullItemTileException, NullPlayersException {
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

 */

    private ShelfInserter shelfInserter;
    private ShelfBuffer shelfBuffer;
    private Player testPlayer;

    @BeforeEach
    public void setUp() {
        shelfInserter = new ShelfInserter();
        testPlayer = new Player();
        shelfInserter.setActivePlayer(testPlayer);
        shelfBuffer = shelfInserter.getShelfBuffer();
    }

    @Test
    public void testGetShelfNullPlayer() {
        try {
            shelfInserter.getShelf();
        } catch (NullPlayersException e) {
            assertEquals(NullPlayersException.class, e.getClass());
        }
    }

    @Test
    public void testGetShelf() throws NullPlayersException {
        Shelf actualShelf = shelfInserter.getShelf();
        assertNotNull(actualShelf);
    }

    @Test
    public void testInsertTilesNullBuffer() throws TooManyTilesException, FullColumnException {
        try{
            shelfInserter.insertTiles(0);
        } catch (NullItemTileException e) {
            assertEquals(NullItemTileException.class, e.getClass());
        }
    }

    @Test
    public void testInsertTilesFullColumn() throws NullItemTileException, TooManyTilesException {
        Shelf shelf = testPlayer.getShelf();

        // Fill column 0 of the shelf grid
        for (int i = 0; i < shelf.getROWS(); i++) {
            ItemTile tile = new ItemTile(Color.PINK);
            shelf.getShelfGrid()[i][0] = tile;
        }

        // Insert a tile in column 0 of the buffer
        shelfBuffer.setTiles(List.of(new ItemTile(Color.YELLOW)));
        shelfBuffer.sortTiles(List.of(Color.YELLOW));
        try {
            shelfInserter.insertTiles(0);
        } catch (FullColumnException e) {
            assertEquals(FullColumnException.class, e.getClass());
        }
    }

    @Test
    public void testInsertTilesTooManyTiles() throws NullItemTileException, FullColumnException {
        Shelf shelf = testPlayer.getShelf();

        // Fill column 0 of the shelf grid except for the bottom cell
        for (int i = 0; i < shelf.getROWS() - 1; i++) {
            ItemTile tile = new ItemTile(Color.PINK);
            shelf.getShelfGrid()[i][0] = tile;
        }
        assertEquals(Color.PINK, shelf.getTileAtLocation(new Point(0, 0)).getColor());

        // Insert two tiles in column 0 of the buffer
        List<ItemTile> tiles = Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE));
        shelfBuffer.setTiles(tiles);
        shelfBuffer.sortTiles(Arrays.asList(Color.YELLOW, Color.BLUE));
        try {
            shelfInserter.insertTiles(0);
        } catch (TooManyTilesException e) {
            assertEquals(TooManyTilesException.class, e.getClass());
        }
    }

    @Test
    public void testInsertTiles() throws NullItemTileException, FullColumnException, TooManyTilesException, NullPlayersException {
        // Set up the buffer with ordered tiles and a non-full column in the shelf
        Shelf shelf = shelfInserter.getShelf();
        List<ItemTile> tiles = Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE));
        shelfBuffer.setTiles(tiles);
        shelfBuffer.sortTiles(Arrays.asList(Color.YELLOW, Color.BLUE));

        // Insert tiles and verify that they were inserted correctly
        assertTrue(shelfInserter.insertTiles(1));
        assertTrue(shelfInserter.insertTiles(1));
        assertTrue(shelfInserter.insertTiles(1));

        assertEquals(Color.YELLOW, shelf.getTileAtLocation(new Point(0, 1)).getColor());
        assertEquals(Color.BLUE, shelf.getTileAtLocation(new Point(1, 1)).getColor());
    }
}
