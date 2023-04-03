package it.polimi.ingsw.model;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    public void setUp() throws TooManyCardsRequestedException {
        game = new Game();
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();
    }

    @Test
    public void testAddPlayer() {

        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size());

        assertTrue(game.addPlayer(player2));
        assertTrue(game.addPlayer(player3));
        assertTrue(game.addPlayer(player4));

        assertEquals(4, game.getPlayers().size());

        assertFalse(game.addPlayer(new Player()));
    }
}