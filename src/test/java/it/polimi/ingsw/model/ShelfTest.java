package it.polimi.ingsw.model;

import it.polimi.ingsw.model.gameItems.Tiles.ItemTile;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShelfTest {

    private Shelf shelf;
    private int ROWS_SIZE;
    private int COLUMNS_SIZE;

    @BeforeAll
    @Test
    public void testInizialization() {
        shelf = new Shelf();
        ROWS_SIZE = shelf.getROWS_SIZE();
        COLUMNS_SIZE = shelf.getCOLUMNS_SIZE();
        List<Stack<ItemTile>> shelfGrid = shelf.getShelfGrid();

        assertEquals(shelfGrid, new ArrayList<>());

        for (int i = 0; i < COLUMNS_SIZE; i++) {
            assertEquals(shelfGrid.get(i), new Stack<>());
        }

    }

    @Test
    public void getNumOfBoxLeftInColTest() {

    }


}