package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShelfBufferTest {

    private ShelfBuffer buffer;

    @BeforeEach
    void setUp() {
        buffer = new ShelfBuffer();
    }

    @Test
    void getTilesToBeInserted() {
        List<ItemTile> expectedTiles = new ArrayList<>();
        expectedTiles.add(new ItemTile(Color.BLUE));
        buffer.setTiles(expectedTiles);
        assertEquals(expectedTiles, buffer.getTiles());
    }

    @Test
    void setUnorderedTilesToBeInserted() {
        List<ItemTile> expectedTiles = new ArrayList<>();
        expectedTiles.add(new ItemTile(Color.BLUE));
        buffer.setTiles(expectedTiles);
        assertEquals(expectedTiles, buffer.getTiles());
    }

    @Test
    void sortTiles() {
        List<ItemTile> unorderedTiles = Arrays.asList(new ItemTile(Color.BLUE), new ItemTile(Color.GREEN), new ItemTile(Color.PINK));

        List<Color> sortedColors = new ArrayList<>();
        sortedColors.add(Color.BLUE);
        sortedColors.add(Color.PINK);
        sortedColors.add(Color.GREEN);

        buffer.setTiles(unorderedTiles);
        buffer.sortTiles(sortedColors);

        List<ItemTile> expectedTiles = new ArrayList<>();
        expectedTiles.add(unorderedTiles.get(0));
        expectedTiles.add(unorderedTiles.get(2));
        expectedTiles.add(unorderedTiles.get(1));

        assertEquals(expectedTiles, buffer.getTiles());
    }

    @Test
    void areTilesOrdered() {
        List<ItemTile> unorderedTiles = new ArrayList<>();
        unorderedTiles.add(new ItemTile(Color.BLUE));
        unorderedTiles.add(new ItemTile(Color.GREEN));
        unorderedTiles.add(new ItemTile(Color.PINK));

        assertFalse(buffer.isOrdered());
        buffer.setTiles(unorderedTiles);
        assertFalse(buffer.isOrdered());

        List<Color> sortedColors = new ArrayList<>();
        sortedColors.add(Color.BLUE);
        sortedColors.add(Color.PINK);
        sortedColors.add(Color.GREEN);

        buffer.sortTiles(sortedColors);
        assertTrue(buffer.isOrdered());
    }
}