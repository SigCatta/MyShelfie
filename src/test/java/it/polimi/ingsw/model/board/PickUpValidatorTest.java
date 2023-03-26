package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.model.board.TilesGetter.PickUpValidator;
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


    /*
    @Test
    public void testNotAdjacentPositions1() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 3));

        assertFalse(PickUpValidator.ArePointsAdjacent(points));
    }

    @Test
    public void testNotAdjacentPositions2() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));
        points.add(new Point(0, 1));
        points.add(new Point(2, 2));

        assertFalse(PickUpValidator.ArePointsAdjacent(points));
    }

    @Test
    public void testNotAdjacentPositions3() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(8, 5));
        points.add(new Point(5, 9));
        points.add(new Point(0, 5));

        assertFalse(PickUpValidator.ArePointsAdjacent(points));
    }

    @Test
    public void testAdjacentPositions1() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));

        assertTrue(PickUpValidator.ArePointsAdjacent(points));
    }

    @Test
    public void testAdjacentPositions2() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 5));
        points.add(new Point(1, 6));
        points.add(new Point(1, 7));

        assertTrue(PickUpValidator.ArePointsAdjacent(points));
    }

    @Test
    public void testAdjacentPositions3() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(8, 5));
        points.add(new Point(6, 5));
        points.add(new Point(7, 5));

        assertTrue(PickUpValidator.ArePointsAdjacent(points));
    }

    @Test
    public void testAdjacentPositions5() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(8, 5));
        points.add(new Point(5, 9));

        assertFalse(PickUpValidator.ArePointsAdjacent(points));
    }

    @Test
    public void testSamePosition1() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 0));
        points.add(new Point(0, 0));

        assertFalse(PickUpValidator.ArePointsAdjacent(points));
    }

    @Test
    public void testSamePosition2() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(100, 0));
        points.add(new Point(100, 0));
        points.add(new Point(0, 0));

        assertFalse(PickUpValidator.ArePointsAdjacent(points));
    }



    @Test
    public void testSamePosition3() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(100, 0));
        points.add(new Point(101, 0));
        points.add(new Point(101, 0));

        assertFalse(PickUpValidator.ArePointsAdjacent(points));

    }

    @Test
    public void testTooMuchTiles() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(0, 3));


        assertFalse(PickUpValidator.isValid(fullGrid, points));
    }

    @Test
    public void testInvalidPoint() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(8, 5));
        points.add(new Point(6, 5));
        points.add(new Point(7, 500));

        assertFalse(PickUpValidator.isValid(fullGrid, points));
    }


    @Test
    public void testFull_1_0() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));


        assertTrue(PickUpValidator.isValid(fullGrid, points));
    }

    @Test
    public void testFull_1_1() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(3, 0));

        assertTrue(PickUpValidator.isValid(fullGrid, points));
    }

    @Test
    public void testFull2_0() {
        ArrayList<Point> points = new ArrayList<>();

        assertFalse(PickUpValidator.isValid(fullGrid, points));
    }

    @Test
    public void testFull2_1() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));

        assertFalse(PickUpValidator.isValid(fullGrid, points));
    }

    @Test
    public void testFull2_2() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 3));
        points.add(new Point(1, 3));
        points.add(new Point(2, 3));


        assertFalse(PickUpValidator.isValid(fullGrid, points));
    }

    @Test
    public void testFull2_3() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 2));
        points.add(new Point(1, 1));


        assertFalse(PickUpValidator.isValid(fullGrid, points));
    }

    @Test
    public void testFull2_4() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(k-2, 2));
        points.add(new Point(k-1, k-1));
        points.add(new Point(k-3, 1));


        assertFalse(PickUpValidator.isValid(fullGrid, points));
    }



    @Test
    public void testEmpty1_0() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 2));

        assertFalse(PickUpValidator.isValid(emptyGrid, points));
    }

    @Test
    public void testEmpty1_1() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 3));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));

        assertFalse(PickUpValidator.isValid(emptyGrid, points));
    }

    @Test
    public void testHalf1_0(){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, k/2 - 1));

        assertTrue(PickUpValidator.isValid(leftHalfGrid, points));
    }

    @Test
    public void testHalf1_1(){
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(1, k/2 - 1));
        points.add(new Point(2, k/2 - 1));
        points.add(new Point(3, k/2 - 1));


        assertTrue(PickUpValidator.isValid(leftHalfGrid, points));
    }

    @Test
    public void testHalf1_2(){
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(1, k/2 - 1));
        points.add(new Point(2, k/2 - 1));
        points.add(new Point(1, k/2 - 1));


        assertFalse(PickUpValidator.isValid(leftHalfGrid, points));
    }
*/
}