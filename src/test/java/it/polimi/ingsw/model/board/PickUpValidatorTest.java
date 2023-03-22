package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PickUpValidatorTest {

    int k = 10;
    private ItemTile[][] fullGrid = new ItemTile[k][k];
    private ItemTile[][] emptyGrid = new ItemTile[k][k];
    private ItemTile[][] leftHalfGrid = new ItemTile[k][k];


    public PickUpValidatorTest(){

        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++){
                fullGrid[i][j] = new ItemTile(Color.PINK);
                emptyGrid[i][j] = null;
                if(j < k/2) {
                    leftHalfGrid[i][j] = new ItemTile(Color.PINK);
                }else{
                    leftHalfGrid[i][j] = null;
                }
            }
        }
    }



    @Test
    public void testFull1() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));


        assertTrue(PickUpValidator.isValid(fullGrid, points));
    }

    @Test
    public void testFull2() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(0, 2));


        assertFalse(PickUpValidator.isValid(fullGrid, points));
    }

}