package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.JSONReader.LookUpTableReader;
import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BoardRefresherTest {

    private BoardRefresher boardRefresher;
    private Game game;

    private int boardSize;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        boardSize = game.getBoard().getSize();

    }

    @Test
    void testItemTilePlacement4() {
        boolean[][] table = new boolean[boardSize][boardSize];

        game.addPlayer(new Player());
        game.addPlayer(new Player());

        boardRefresher = new BoardRefresher(game);

        boardRefresher.refillBoard();

        LookUpTableReader lookUpTableReader = new LookUpTableReader();
        boolean[][] gottenTable = lookUpTableReader.getLookUpTable(game.getPlayers().size());

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                if(gottenTable[i][j]){
                    assertNotNull(game.getBoard().getBoardGrid()[i][j]);
                }else{
                    assertNull(game.getBoard().getBoardGrid()[i][j]);
                }
            }
        }
    }

    @Test
    void testItemTilePlacement3() {
        boolean[][] table = new boolean[boardSize][boardSize];

        game.addPlayer(new Player());

        boardRefresher = new BoardRefresher(game);

        boardRefresher.refillBoard();

        LookUpTableReader lookUpTableReader = new LookUpTableReader();
        boolean[][] gottenTable = lookUpTableReader.getLookUpTable(game.getPlayers().size());

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                if(gottenTable[i][j]){
                    assertNotNull(game.getBoard().getBoardGrid()[i][j]);
                }else{
                    assertNull(game.getBoard().getBoardGrid()[i][j]);
                }
            }
        }
    }

    @Test
    void testItemTilePlacement2() {
        boolean[][] table = new boolean[boardSize][boardSize];

        boardRefresher = new BoardRefresher(game);

        boardRefresher.refillBoard();

        LookUpTableReader lookUpTableReader = new LookUpTableReader();
        boolean[][] gottenTable = lookUpTableReader.getLookUpTable(game.getPlayers().size());

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                if(gottenTable[i][j]){
                    assertNotNull(game.getBoard().getBoardGrid()[i][j]);
                }else{
                    assertNull(game.getBoard().getBoardGrid()[i][j]);
                }
            }
        }
    }


}