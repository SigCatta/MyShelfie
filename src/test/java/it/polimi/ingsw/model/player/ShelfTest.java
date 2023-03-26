package it.polimi.ingsw.model.player;


import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class ShelfTest {
    private Shelf shelf;
    private int ROWS;
    private int COLUMNS;

    @BeforeEach
    public void setUp(){
        shelf = new Shelf();
        ROWS = shelf.getROWS();
        COLUMNS = shelf.getCOLUMNS();
    }
    @Test
    public void inizializationTest() {
        List<Stack<ItemTile>> shelfGrid = shelf.getShelfGrid();

        for (int i = 0; i < COLUMNS; i++) {
            assertEquals(new Stack<>(), shelfGrid.get(i));
        }
    }

    @Test
    public void getNumOfBoxLeftInColTest() {
        List<Stack<ItemTile>> shelfGrid = shelf.getShelfGrid();
        for (int i = 0; i < COLUMNS; i++) {
            assertEquals(ROWS, shelf.getNumOfBoxLeftInCol(i));
        }

        for (int i = 0; i < COLUMNS; i++) {
            shelfGrid.add(new Stack<>());
            for (int j = 0; j < Math.max((ROWS - i), 0); j++) {
                shelfGrid.get(i).add(new ItemTile(Color.BLUE));
            }
            assertEquals(Math.max((ROWS - i), 0), shelfGrid.get(i).size());
            assertEquals(ROWS- Math.max((ROWS - i), 0), shelf.getNumOfBoxLeftInCol(i));
        }
    }

    @Test
    public void isColumnFulTestl() {
        List<Stack<ItemTile>> shelfGrid = shelf.getShelfGrid();

        for (int i = 0; i < COLUMNS; i++) {
            assertFalse(shelf.isColumnFull(i));
        }
        for (int j = 0; j < ROWS; j++) {
            shelfGrid.get(0).add(new ItemTile(Color.BLUE));
        }
        assertTrue(shelf.isColumnFull(0));
    }

    @Test
    public void getTileAtLocationTest() {
        List<Stack<ItemTile>> shelfGrid = shelf.getShelfGrid();

        for (int i = 0; i < COLUMNS; i++) {
            shelfGrid.add(new Stack<>());
            for (int j = 0; j < Math.max((ROWS - i), 0); j++) {
                ItemTile itemTile = new ItemTile(Color.BLUE);
                shelfGrid.get(i).add(itemTile);
                Point pos = new Point(j, i);
                assertEquals(itemTile, shelf.getTileAtLocation(pos));
            }
        }
        Point pos = new Point(ROWS+1, 0);
        assertNull(shelf.getTileAtLocation(pos));
    }

}