package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;
    private int BOARD_SIZE = 5;

    @BeforeEach
    void setUp() {
        board = new Board(BOARD_SIZE);
    }

    @Test
    void testGetBoardGrid() {
        ItemTile[][] grid = board.getBoardGrid();
        assertNotNull(grid);
        assertEquals(BOARD_SIZE, grid.length);
        assertEquals(BOARD_SIZE, grid[0].length);
    }

    @Test
    void testSetAndGetItemTile() {
        Point location = new Point(1, 2);
        Color color = Color.YELLOW;
        board.setItemTile(color, location.x, location.y);
        assertEquals(color, board.getBoardGrid()[location.x][location.y].getColor());
    }

    @Test
    void testRemoveItemTile() {
        Point location = new Point(3, 3);
        Color color = Color.PINK;
        ItemTile tile = new ItemTile(color);
        board.getBoardGrid()[location.x][location.y] = tile;
        ItemTile removedTile = board.removeItemTile(location);
        assertNull(board.getBoardGrid()[location.x][location.y]);
        assertEquals(tile, removedTile);
    }
}