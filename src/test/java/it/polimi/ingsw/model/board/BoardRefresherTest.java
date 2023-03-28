package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.model.player.Player;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BoardRefresherTest {
    Game game = new Game();

    public BoardRefresherTest() throws IOException, ParseException {
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
    }

    @Test
    public void testHalf1_2(){
        game.getBoardRefresher().refillBoard();
    }

}