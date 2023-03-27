package it.polimi.ingsw.board;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Test;

public class BoardRefresherTest {
    Game game = new Game();

    public BoardRefresherTest(){
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
    }

    @Test
    public void testHalf1_2(){
        game.getBoardRefresher().refillBoard();
        System.out.println("a");
    }

}
