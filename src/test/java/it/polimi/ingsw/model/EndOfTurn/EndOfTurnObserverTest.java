package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.JSONReader.LookUpTableReader;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class EndOfTurnObserverTest {

    Game game = new Game();
    int boardSize;


    @BeforeEach
    public void setUp(){
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        boardSize = game.getBoard().getSize();
    }

    @Test
    public void test2Players(){
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
    public void test3Players(){

        game.addPlayer(new Player());

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
    public void test4Players(){

        game.addPlayer(new Player());
        game.addPlayer(new Player());

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
    public void test1Tile(){

        game.addPlayer(new Player());
        game.addPlayer(new Player());

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