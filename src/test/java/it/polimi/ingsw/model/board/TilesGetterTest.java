package it.polimi.ingsw.model.board;

import exceptions.FullColumnException;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;
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

class TilesGetterTest {
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
        game.setActivePlayer(player);
        chosenPositions = new ArrayList<>();
        game.start();
    }

    @Test
    void testPickUpTiles1() {

    }

    @Test
    void testSendTilesToShelf1() {

    }

    @Test
    void testSendTilesToShelf2() {


    }

    @Test
    void testPositionAlreadySelected() {


    }

    @Test
    void testWrongColumnSelected() {

    }

    @Test
    void testNullTilesSentToShelf() throws FullColumnException {

    }

}



