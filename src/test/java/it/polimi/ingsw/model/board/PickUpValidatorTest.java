package it.polimi.ingsw.model.board;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.ChosenTilesTable.PickUpValidator;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PickUpValidatorTest {
    private Game game;
    private Board board;
    private PickUpValidator pickUpValidator;

    @BeforeEach
    public void setUp() throws TooManyCardsRequestedException {
        game = new Game(4);
        game.getBoard().emptyBoard();
        game.start();
        board = game.getBoard();
    }

    @Test
    public void testIsValidWithTooManyTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(0, 1));
        chosenPositions.add(new Point(0, 2));
        chosenPositions.add(new Point(0, 3));
        chosenPositions.add(new Point(0, 4));

    }

    @Test
    public void testIsValidWithNoTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();


    }

    @Test
    public void testIsValidWithNonAdjacentTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(1, 1));
        chosenPositions.add(new Point(2, 2));

    }

    @Test
    public void testIsValidWithEmptyTile() {
        ItemTile[][] boardGrid = board.getBoardGrid();
        boardGrid[0][0] = null;

        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(0, 1));

    }

    @Test
    public void testIsValidWithNoFreeAdjacentNeighbor() {
        ItemTile[][] boardGrid = board.getBoardGrid();
        boardGrid[0][0] = new ItemTile(Color.GREEN);
        boardGrid[0][1] = new ItemTile(Color.GREEN);
        boardGrid[1][0] = new ItemTile(Color.GREEN);

        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(0, 1));

    }

    @Test
    public void testIsValidWithValidTiles() {
        ItemTile[][] boardGrid = board.getBoardGrid();
        boardGrid[0][0] = new ItemTile(Color.GREEN);
        boardGrid[0][1] = new ItemTile(Color.GREEN);
        boardGrid[1][0] = new ItemTile(Color.GREEN);

        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(1, 0));

    }


}