package it.polimi.ingsw.model.board;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
import exceptions.TooManyCardsRequestedException;
import exceptions.TooManyPlayersException;
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
    void setUp() throws TooManyPlayersException, TooManyCardsRequestedException {
        game = new Game();
        board = game.getBoard();
        player = new Player();
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
        assertTrue(tilesGetter.getTilesToBeInserted().get(0).getColor().equals(Color.YELLOW));
        assertFalse(tilesGetter.pickUpTiles(chosenPositions));  //same tiles already picked up
        chosenPositions.remove(goodPosition);
        chosenPositions.add(badPosition);
        assertFalse(tilesGetter.pickUpTiles(chosenPositions));  //one bad position
    }

    @Test
    void testEnoughFreeCellsInCol() throws NullItemTileException, FullColumnException {
        int column = 0;
        ItemTile toInsert = new ItemTile(Color.YELLOW);

        assertTrue(tilesGetter.enoughFreeCellsInCol(column));
        for (int i = 0; i < player.getShelf().getROWS(); i++) {
            tilesGetter.sendTilesToShelf(toInsert, column);
        }
        tilesGetter.getTilesToBeInserted().add(toInsert);
        assertFalse(tilesGetter.enoughFreeCellsInCol(column));
    }

    @Test
    void testSendTilesToShelf() throws NullItemTileException, FullColumnException {
        int column = 1;
        ItemTile toInsert = new ItemTile(Color.YELLOW);
        for (int i = 0; i < player.getShelf().getROWS(); i++) {
            assertTrue(tilesGetter.sendTilesToShelf(toInsert, column));
            assertEquals(toInsert, player.getShelf().getTileAtLocation(new Point(0, column)));
        }
        try {
            tilesGetter.sendTilesToShelf(toInsert, column);
        } catch (FullColumnException e) {
            assertEquals(FullColumnException.class, e.getClass());   //column already full
        }

    }

    @Test
    void testNullTilesSentToShelf() throws NullItemTileException, FullColumnException {
        int column = 1;
        ItemTile toInsert = null;
        try {
            tilesGetter.sendTilesToShelf(toInsert, column);
        } catch (NullItemTileException e) {
            assertEquals(NullItemTileException.class, e.getClass());
        }

    }

}



