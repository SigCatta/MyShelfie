package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.AdjacencyScoreCalculation;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class AdjacencyScoreCalculationTest {

    Player activePlayer = new Player();

    @Test
    public void test1(){
        Assertions.assertEquals(AdjacencyScoreCalculation.calculateScore(activePlayer), 0);
    }

    @Test
    public void test2(){
        activePlayer.getShelf().getShelfGrid()[0][0] = new ItemTile(Color.WHITE);
        activePlayer.getShelf().getShelfGrid()[0][1] = new ItemTile(Color.WHITE);
        activePlayer.getShelf().getShelfGrid()[0][2] = new ItemTile(Color.WHITE);
        activePlayer.getShelf().getShelfGrid()[0][3] = new ItemTile(Color.WHITE);
        activePlayer.getShelf().getShelfGrid()[0][4] = new ItemTile(Color.WHITE);

        activePlayer.getShelf().getShelfGrid()[1][0] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[2][0] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[3][0] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[4][0] = new ItemTile(Color.BLUE);

        Assertions.assertEquals(AdjacencyScoreCalculation.calculateScore(activePlayer), 8);
    }

    @Test
    public void test3(){
        //3 points
        activePlayer.getShelf().getShelfGrid()[5][0] = new ItemTile(Color.WHITE);
        activePlayer.getShelf().getShelfGrid()[5][1] = new ItemTile(Color.WHITE);
        activePlayer.getShelf().getShelfGrid()[4][1] = new ItemTile(Color.WHITE);
        activePlayer.getShelf().getShelfGrid()[3][1] = new ItemTile(Color.WHITE);

        //8 points
        activePlayer.getShelf().getShelfGrid()[3][0] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[2][0] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[2][1] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[2][2] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[2][3] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[3][2] = new ItemTile(Color.BLUE);
        activePlayer.getShelf().getShelfGrid()[2][4] = new ItemTile(Color.BLUE);


        Assertions.assertEquals(AdjacencyScoreCalculation.calculateScore(activePlayer), 11);
    }


}