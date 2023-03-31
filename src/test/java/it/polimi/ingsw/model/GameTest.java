package it.polimi.ingsw.model;

import exceptions.TooManyPlayersException;
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
    public void setUp() {
        game = new Game();
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();
    }

    @Test
    public void testAddPlayer() throws TooManyPlayersException {

        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size());

        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        assertEquals(4, game.getPlayers().size());

        assertThrows(TooManyPlayersException.class, () -> game.addPlayer(new
                Player()));
    }
}