package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TurnHandlerTest {

    private Game game;


    @BeforeEach
    public void setup() {
        game = new Game(4);
        game.addPlayer(new Player("a"));
        game.addPlayer(new Player("b"));
        game.addPlayer(new Player("c"));
        game.addPlayer(new Player("d"));

        game.start();
    }

    @Test
    public void changeTurnTest() {
        TurnHandler turnHandler = game.getTurnHandler();

        Assertions.assertEquals(game.getActivePlayer().getNickname(), "a");

        turnHandler.changeTurn();

        Assertions.assertEquals(game.getActivePlayer().getNickname(), "b");

        turnHandler.changeTurn();

        Assertions.assertEquals(game.getActivePlayer().getNickname(), "c");

        turnHandler.changeTurn();

        Assertions.assertEquals(game.getActivePlayer().getNickname(), "d");

        turnHandler.changeTurn();

        Assertions.assertEquals(game.getActivePlayer().getNickname(), "a");

        game.getPlayers().get(1).setConnected(false);
        game.getPlayers().get(2).setConnected(false);
        game.getPlayers().get(3).setConnected(false);

        turnHandler.changeTurn(); //should end the game

        Assertions.assertEquals(game.getGameState(), GameState.END);

    }
}
