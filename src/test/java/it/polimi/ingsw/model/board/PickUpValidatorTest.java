package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.ChosenTilesTable.PickUpValidator;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PickUpValidatorTest {


    @Test
    public void testIsValidWithTooManyTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(0, 1));
        chosenPositions.add(new Point(0, 2));
        chosenPositions.add(new Point(0, 3));
        chosenPositions.add(new Point(0, 4));

        assertFalse(PickUpValidator.isValid(new Game(2), chosenPositions));

    }

    @Test
    public void testIsValidWithNoTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();

        assertFalse(PickUpValidator.isValid(new Game(2), chosenPositions));
    }

    @Test
    public void testIsValidWithNonAdjacentTiles() {
        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(1, 1));
        chosenPositions.add(new Point(2, 2));

        assertFalse(PickUpValidator.isValid(new Game(2), chosenPositions));

    }

    @Test
    public void testIsValid1() {

        Game game = new Game(2);
        game.setActivePlayer(new Player("g"));
        game.getBoard().getBoardGrid()[0][0] = new ItemTile(Color.GREEN);
        game.getBoard().getBoardGrid()[0][1] = new ItemTile(Color.GREEN);
        game.getBoard().getBoardGrid()[1][0] = new ItemTile(Color.GREEN);

        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(0, 1));

        assertTrue(PickUpValidator.isValid(game, chosenPositions));

    }

    @Test
    public void testIsValid2() {

        Game game = new Game(2);
        game.setActivePlayer(new Player("g"));
        game.getBoard().getBoardGrid()[0][0] = new ItemTile(Color.GREEN);
        game.getBoard().getBoardGrid()[0][1] = new ItemTile(Color.GREEN);
        game.getBoard().getBoardGrid()[1][0] = new ItemTile(Color.GREEN);

        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(0, 1));

        assertTrue(PickUpValidator.isValid(game, chosenPositions));

    }

    @Test
    public void testIsValid3() {
        Game game = new Game(2);
        game.setActivePlayer(new Player("g"));
        game.getBoard().getBoardGrid()[0][0] = new ItemTile(Color.GREEN);
        game.getBoard().getBoardGrid()[0][1] = new ItemTile(Color.GREEN);
        game.getBoard().getBoardGrid()[1][0] = new ItemTile(Color.GREEN);

        ArrayList<Point> chosenPositions = new ArrayList<>();
        chosenPositions.add(new Point(0, 0));
        chosenPositions.add(new Point(1, 0));
        chosenPositions.add(new Point(2, 0));

        assertFalse(PickUpValidator.isValid(game, chosenPositions));

    }


}