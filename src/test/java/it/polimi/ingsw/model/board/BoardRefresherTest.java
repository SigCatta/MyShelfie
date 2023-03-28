package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Bag;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BoardRefresherTest {

    private Board board;
    private Bag bag;
    private int numberOfPlayers;
    private BoardRefresher boardRefresher;

    @BeforeEach
    public void setUp() {
        board = new Board(5);
        bag = new Bag();
        numberOfPlayers = 3;
        boardRefresher = new BoardRefresher(board, bag, numberOfPlayers);
    }

}