package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.RefreshTrigger;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RefreshTriggerTest {

    public Board board1;
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game(4);
        game.getBoard().emptyBoard();
        game.start();
        board1 = game.getBoard();
    }

    @Test
    void testNotRefreshable() {
        // Create a board with non isolated tiles
        board1.setItemTile(Color.PINK, 0, 0);
        board1.setItemTile(Color.PINK, 0, 2);
        board1.setItemTile(Color.PINK, 1, 0);
        board1.setItemTile(Color.PINK, 1, 1);
        board1.setItemTile(Color.PINK, 1, 2);
        board1.setItemTile(Color.PINK, 2, 0);
        board1.setItemTile(Color.PINK, 0, 1);
        board1.setItemTile(Color.PINK, 2, 1);
        board1.setItemTile(Color.PINK, 2, 2);

        assertFalse(RefreshTrigger.isBoardRefreshable(board1));
    }

    @Test
    void testRefreshable() {

        board1.emptyBoard();
        // Create a board with isolated tiles
        board1.setItemTile(Color.BLUE, 0, 0);
        board1.setItemTile(Color.BLUE, 0, 2);
        board1.setItemTile(Color.LIGHTBLUE, 2, 0);
        board1.setItemTile(Color.LIGHTBLUE, 2, 6);
        board1.setItemTile(Color.LIGHTBLUE, 3, 4);
        board1.setItemTile(Color.LIGHTBLUE, 1, 1);

        assertTrue(RefreshTrigger.isBoardRefreshable(board1));
    }
}
