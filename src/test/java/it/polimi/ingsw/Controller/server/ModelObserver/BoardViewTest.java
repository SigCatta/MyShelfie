package it.polimi.ingsw.Controller.server.ModelObserver;

import it.polimi.ingsw.VirtualView.ModelObservers.BoardVV;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class BoardViewTest {
    BoardVV boardView;
    VirtualView virtualView;
    Game game;
    @BeforeEach
    public void setup(){
        game = new Game(3);
        game.start();
        BoardRefresher boardRefresher = new BoardRefresher(game);
        boardRefresher.refillBoard();
        virtualView = new VirtualView(game);
        boardView = new BoardVV(game, virtualView);
    }

    @Test
    public void isObserver(){
        game.getBoard().removeItemTile(new Point(6,6));
    }
}
