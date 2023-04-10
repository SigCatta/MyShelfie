package it.polimi.ingsw.model.EndOfTurn;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreBoardTest {
    Game game;
    List<Player> players;
    @BeforeEach
    public void init() throws TooManyCardsRequestedException {
        game = new Game();
        game.addPlayer(new Player("player1"));
        game.addPlayer(new Player("player2"));
        game.addPlayer(new Player("player3"));
        game.addPlayer(new Player("player4"));
        new TurnHandler(game);
        players = game.getPlayers();
    }
    @Test
    public void getWinnerTest1() { // general case
        players.get(0).updateScore(1);
        players.get(1).updateScore(2);
        players.get(2).updateScore(2);
        players.get(3).updateScore(3);
        assertEquals(game.getWinner(), players.get(3));
    }

    @Test
    public void getWinnerTest2() { // if two or more players have the same ammount of points, the player who played last should be the winner
        players.get(0).updateScore(1);
        players.get(1).updateScore(2);
        players.get(2).updateScore(3);
        players.get(3).updateScore(3);
        assertEquals(game.getWinner(), players.get(3));
    }

    @Test
    public void getWinnerTest3() { // if all players have the same ammount of points, the player who played last should be the winner
        players.get(0).updateScore(3);
        players.get(1).updateScore(3);
        players.get(2).updateScore(3);
        players.get(3).updateScore(3);
        assertEquals(game.getWinner(), players.get(3));
    }
}
