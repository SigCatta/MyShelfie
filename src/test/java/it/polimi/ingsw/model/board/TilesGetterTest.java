package it.polimi.ingsw.model.board;

import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

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
    void testNullTilesSentToShelf() {

    }

}



