package it.polimi.ingsw.model.EndOfTurn;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.JSONReader.LookUpTableReader;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EndOfTurnObserverTest {

    Game game;
    int boardSize;


    @BeforeEach
    public void setUp() throws TooManyCardsRequestedException {
        game = new Game(4);
        game.addPlayer(new Player("player1"));
        game.addPlayer(new Player("player2"));
        boardSize = game.getBoard().getSize();
    }

    @Test
    public void test2Players() {
        //the board should be refilled
        game.start();
        game.setActivePlayer(game.getPlayers().get(0));

        game.getTurnHandler().notifyObservers();

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
    public void test3Players() {
        game.addPlayer(new Player("player3"));
        game.start();
        game.setActivePlayer(game.getPlayers().get(0));

        //the board should be refilled
        game.getTurnHandler().notifyObservers();

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
    public void test4Players() {
        game.addPlayer(new Player("player3"));
        game.addPlayer(new Player("player4"));
        game.start();

        game.setActivePlayer(game.getPlayers().get(0));

        //the board should be refilled
        game.getTurnHandler().notifyObservers();

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
    public void test1Tile() {

        game.addPlayer(new Player("player3"));
        game.addPlayer(new Player("player4"));
        game.start();
        game.setActivePlayer(game.getPlayers().get(0));

        game.getBoard().getBoardGrid()[3][5] = new ItemTile(Color.WHITE);

        //the board should be refilled
        game.getTurnHandler().notifyObservers();

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

        assertEquals(game.getBoard().getBoardGrid()[3][5].getColor(), Color.WHITE);

    }

}