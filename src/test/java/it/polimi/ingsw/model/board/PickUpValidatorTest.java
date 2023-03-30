package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.TilesGetter.PickUpValidator;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PickUpValidatorTest {
    private Game game;
    private Board board;
    private PickUpValidator pickUpValidator;

    @BeforeEach
    public void setUp() {
        game = new Game();
        board = game.getBoard();
        pickUpValidator = new PickUpValidator(game);
    }

    @Test
    public void testIsValidWithTooManyTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(0, 1));
        chosenPositions.add(new Point(0, 2));
        chosenPositions.add(new Point(0, 3));
        chosenPositions.add(new Point(0, 4));

        assertFalse(pickUpValidator.isValid(chosenPositions));
    }

    @Test
    public void testIsValidWithNoTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();

        assertFalse(pickUpValidator.isValid(chosenPositions));
    }

    @Test
    public void testIsValidWithNonAdjacentTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(1, 1));
        chosenPositions.add(new Point(2, 2));

        assertFalse(pickUpValidator.isValid(chosenPositions));
    }

    @Test
    public void testIsValidWithEmptyTile() {
        ItemTile[][] boardGrid = board.getBoardGrid();
        boardGrid[0][0] = null;

        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(0, 1));

        assertFalse(pickUpValidator.isValid(chosenPositions));
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

        assertTrue(pickUpValidator.isValid(chosenPositions));
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

        assertTrue(pickUpValidator.isValid(chosenPositions));
    }


}