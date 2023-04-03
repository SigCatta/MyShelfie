package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FullShelfObserverTest {

    private Game game;
    private Player player;
    private Shelf shelf;
    private FullShelfObserver observer;

    @BeforeEach
    public void setUp() {
        game = new Game();
        player = new Player();
        shelf = player.getShelf();
        observer = new FullShelfObserver(game);
    }

    @Test
    public void testShelfFullCallsUpdate() {

    }

    @Test
    public void testUpdateNotCalledIfShelfNotFull() {

    }

    @Test
    public void testUpdateCalledIfShelfFull() {

    }


}