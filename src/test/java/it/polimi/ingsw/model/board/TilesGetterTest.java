package it.polimi.ingsw.model.board;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TilesGetterTest {
    private TilesGetter tilesGetter;
    private Board board;
    private Game game;
    private Player player;
    private Shelf shelf;
    private ArrayList<Point> chosenPositions;

    @BeforeEach
    void setUp()  {
        game = new Game();
        game.start();
        board = game.getBoard();
        player = new Player("player");
        shelf = player.getShelf();
        game.addPlayer(player);
        tilesGetter = new TilesGetter(game);
        tilesGetter.setActivePlayer(player);
        chosenPositions = new ArrayList<>();
    }

    @Test
    void testPickUpTiles() {
        Point goodPosition = new Point(2, 2);
        Point badPosition = new Point(4, 4);
        board.setItemTile(Color.YELLOW, goodPosition.x, goodPosition.y);
        chosenPositions.add(goodPosition);
        assertTrue(tilesGetter.pickUpTiles(chosenPositions));
        assertEquals(1, tilesGetter.getTilesToBeInserted().size());
        assertEquals(tilesGetter.getTilesToBeInserted().get(0).getColor(), Color.YELLOW);
        assertFalse(tilesGetter.pickUpTiles(chosenPositions));  //same tiles already picked up
        chosenPositions.remove(goodPosition);
        chosenPositions.add(badPosition);
        assertFalse(tilesGetter.pickUpTiles(chosenPositions));  //one bad position
    }

    @Test
    void testEnoughFreeCellsInCol()  {
        tilesGetter.setChosenColumn(0);
        int column = 0;
        ItemTile toInsert = new ItemTile(Color.YELLOW);

        assertTrue(tilesGetter.enoughFreeCellsInCol(column));
        for (int i = 0; i < shelf.getROWS(); i++) {
            shelf.setTileAtLocation(new Point(i, 0), new ItemTile(Color.LIGHTBLUE));
        }
        tilesGetter.getTilesToBeInserted().add(toInsert);
        assertFalse(tilesGetter.enoughFreeCellsInCol(column));
    }

    @Test
    void testSendTilesToShelf() throws NullItemTileException, FullColumnException {
        tilesGetter.setChosenColumn(1);
        int column = 1;
        List<ItemTile> toInsert = Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.PINK));
        for(ItemTile itemTile: toInsert) {
            tilesGetter.getTilesToBeInserted().add(itemTile);
        }
        for(int i = 0; i < toInsert.size(); i++) {
            assertEquals(tilesGetter.getTilesToBeInserted().get(i), toInsert.get(i));
        }

        for (int i = 0; i < shelf.getROWS()-2; i++) {
            shelf.setTileAtLocation(new Point(i, 1), new ItemTile(Color.LIGHTBLUE));
        }
        for (int i = 0; i < 2; i++) {
            assertTrue(tilesGetter.sendTilesToShelf(i, column));
            assertEquals(toInsert.get(i).getColor(), player.getShelf().getTileAtLocation(new Point(shelf.getROWS()-2+i, column)).getColor());
        }
        try {
            tilesGetter.sendTilesToShelf(2, column);
        } catch (FullColumnException e) {
            assertEquals(FullColumnException.class, e.getClass());   //column already full
        }

    }

    @Test
    void testWrongColumnSelected() throws NullItemTileException, FullColumnException {
        tilesGetter.setChosenColumn(0);
        int column = 1;
        assertFalse(tilesGetter.sendTilesToShelf(0, column));
    }

    @Test
    void testNullTilesSentToShelf() throws FullColumnException {
        tilesGetter.setChosenColumn(1);
        int column = 1;
        tilesGetter.getTilesToBeInserted().add(null);

        try {

            tilesGetter.sendTilesToShelf(0, column);
        } catch (NullItemTileException e) {
            assertEquals(NullItemTileException.class, e.getClass());
        }
    }

}



