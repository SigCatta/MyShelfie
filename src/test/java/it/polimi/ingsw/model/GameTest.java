package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    public void setUp() throws TooManyCardsRequestedException {
        game = new Game(4);
        player1 = new Player("player1");
        player2 = new Player("player2");
        player3 = new Player("player3");
        player4 = new Player("player4");
    }

    @Test
    public void testAddPlayer() {

        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size());

        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        assertEquals(4, game.getPlayers().size());

    }
}