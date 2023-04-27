package it.polimi.ingsw.model.board;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
import it.polimi.ingsw.View.VirtualView.VirtualView;
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
        game = new Game(4);
        game.setVirtualView(new VirtualView(game));
        board = game.getBoard();
        player = new Player("player");
        shelf = player.getShelf();
        game.addPlayer(player);
        tilesGetter = new TilesGetter(game);
        game.setActivePlayer(player);
        chosenPositions = new ArrayList<>();
        game.start();
    }

    @Test
    void testPickUpTiles1() {
        Point goodPosition = new Point(2, 2);
        Point badPosition = new Point(4, 4);
        board.setItemTile(Color.YELLOW, goodPosition.x, goodPosition.y);
        chosenPositions.add(goodPosition);
        assertTrue(tilesGetter.pickUpTiles(chosenPositions));
        assertEquals(1, tilesGetter.getChosenTilesTable().size());
        assertEquals(tilesGetter.getChosenTilesTable().getTile(0).getColor(), Color.YELLOW);
        assertFalse(tilesGetter.pickUpTiles(chosenPositions));  //same tiles already picked up
        chosenPositions.remove(goodPosition);
        chosenPositions.add(badPosition);
        assertFalse(tilesGetter.pickUpTiles(chosenPositions));  //one bad position
    }

    @Test
    void testSendTilesToShelf1() {
        int column = 1;
        List<ItemTile> toInsert = Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.PINK));
        for(ItemTile itemTile: toInsert) {
            tilesGetter.getChosenTilesTable().insertTile(itemTile);
            tilesGetter.getPositionsAlreadySelected().add(false);
        }
        for(int i = 0; i < toInsert.size(); i++) {
            assertEquals(tilesGetter.getChosenTilesTable().getTile(i), toInsert.get(i));
        }

        for (int i = 0; i < shelf.getROWS()-2; i++) {
            shelf.setTileAtLocation(new Point(i, 1), new ItemTile(Color.LIGHTBLUE));
        }
        for (int i = 0; i < 3; i++) {
            assertFalse(tilesGetter.sendTilesToShelf(i, column));
        }

        assertFalse(tilesGetter.sendTilesToShelf(2, column));

    }

    @Test
    void testSendTilesToShelf2() {
        int column = 0;
        List<ItemTile> toInsert = Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.PINK));

        for(ItemTile itemTile: toInsert) {
            tilesGetter.getChosenTilesTable().insertTile(itemTile);
            tilesGetter.getPositionsAlreadySelected().add(false);
        }


        shelf.insertTile(new ItemTile(Color.GREEN), 0);
        shelf.insertTile(new ItemTile(Color.GREEN), 0);
        shelf.insertTile(new ItemTile(Color.GREEN), 0);
        shelf.insertTile(new ItemTile(Color.GREEN), 0);
        shelf.insertTile(new ItemTile(Color.GREEN), 0);


        assertFalse(tilesGetter.sendTilesToShelf(0, column)); //because 3 tiles can't fit in this column

        column = 1;

        for (int i = 0; i < 2; i++) {
            assertTrue(tilesGetter.sendTilesToShelf(i, column));
        }

        assertFalse(tilesGetter.sendTilesToShelf(2, 3));
        assertFalse(tilesGetter.sendTilesToShelf(1, column));

    }

    @Test
    void testPositionAlreadySelected() {
        int column = 1;
        List<ItemTile> toInsert = Arrays.asList(new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.PINK));
        for(ItemTile itemTile: toInsert) {
            tilesGetter.getChosenTilesTable().insertTile(itemTile);
            tilesGetter.getPositionsAlreadySelected().add(false);
        }

        assertTrue(tilesGetter.sendTilesToShelf(0, column));
        assertFalse(tilesGetter.sendTilesToShelf(10, column));

    }

    @Test
    void testWrongColumnSelected() {
        int column = 1;
        assertFalse(tilesGetter.sendTilesToShelf(0, column));
    }

    @Test
    void testNullTilesSentToShelf() throws FullColumnException {
        int column = 1;
        tilesGetter.getChosenTilesTable().insertTile(null);
        tilesGetter.getPositionsAlreadySelected().add(false);

        assertFalse(tilesGetter.sendTilesToShelf(0, column));
    }

}



