package it.polimi.ingsw.model.player;


import exceptions.NullItemTileException;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.awt.*;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class ShelfTest {
    private Shelf testShelf;

    private int ROWS;
    private int COLUMNS;

    @BeforeEach
    public void setUp(){
        testShelf = new Shelf();
        ROWS = testShelf.getROWS();
        COLUMNS = testShelf.getCOLUMNS();
    }
    @Test
    public void inizializationTest() {
        ItemTile[][] shelfGrid = testShelf.getShelfGrid();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                assertNull(shelfGrid[i][j]);
            }
        }
    }

    @Test
    public void testSetTileAtLocation()  {
        // Test getting tile from non-empty location
        ItemTile testTile = new ItemTile(Color.BLUE);
        Point nonEmptyLocation = new Point(2,4);
        testShelf.setTileAtLocation(nonEmptyLocation, testTile);
        assertEquals(testTile, testShelf.getTileAtLocation(nonEmptyLocation));
    }

    @Test
    public void testGetTileAtLocation()  {
        // Test getting tile from empty location
        Point emptyLocation = new Point(3,3);
        assertEquals(null,testShelf.getTileAtLocation(emptyLocation) );


        // Test getting tile from non-empty location
        ItemTile testTile = new ItemTile(Color.BLUE);
        Point nonEmptyLocation = new Point(2,4);
        testShelf.getShelfGrid()[2][4] = testTile;
        assertEquals(testTile, testShelf.getTileAtLocation(nonEmptyLocation));
    }

    @Test
    public void testGetNumOfBoxLeftInCol() {
        // Test getting number of boxes in empty column
        int emptyColumn = 1;
        assertEquals(ROWS, testShelf.getNumOfBoxLeftInCol(emptyColumn));

        // Test getting number of boxes in partially filled column
        ItemTile testTile = new ItemTile(Color.BLUE);
        testShelf.getShelfGrid()[4][0] = testTile;
        testShelf.getShelfGrid()[2][0] = testTile;
        assertEquals(4, testShelf.getNumOfBoxLeftInCol(0));

        // Test getting number of boxes in completely filled column
        for (int i = 0; i < ROWS; i++) {
            testShelf.getShelfGrid()[i][2] = testTile;
        }
        assertEquals(0, testShelf.getNumOfBoxLeftInCol(2));
    }

    @Test
    public void testIsColumnFull() {
        // Test empty column
        int emptyColumn = 3;
        assertFalse(testShelf.isColumnFull(emptyColumn));

        // Test partially filled column
        ItemTile testTile = new ItemTile(Color.BLUE);
        testShelf.getShelfGrid()[3][1] = testTile;
        assertFalse(testShelf.isColumnFull(1));

        // Test completely filled column
        for (int i = 0; i < ROWS; i++) {
            testShelf.getShelfGrid()[i][4] = testTile;
        }
        assertTrue(testShelf.isColumnFull(4));
    }

}