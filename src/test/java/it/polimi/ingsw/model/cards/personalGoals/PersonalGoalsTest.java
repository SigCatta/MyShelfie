package it.polimi.ingsw.model.cards.personalGoals;


import it.polimi.ingsw.exceptions.NullPlayersException;
import it.polimi.ingsw.exceptions.TooManyPlayersException;
import it.polimi.ingsw.JSONReader.PersonalGoalReader;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests {@link PersonalGoal}'s methods
 */
public class PersonalGoalsTest {

    @Test
    public void pointStackTest() throws TooManyPlayersException, IOException, ParseException, NullPlayersException {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("player1"));
        PersonalCardDealer.getCards(players);

        PersonalGoal personalGoal = players.get(0).getPersonalGoal();
        Stack<Integer> pointStack = new PersonalGoalReader().getPointStack();
        assertEquals(pointStack, personalGoal.getPointStack()); // full Stack
    }


    private PersonalGoal personalGoal;
    private Player player;
    private HashMap<Color, Point> achievements;
    private Stack<Integer> points;

    @BeforeEach
    public void setUp() {
        player = new Player("player");
        achievements = new HashMap<>();
        achievements.put(Color.PINK, new Point(1, 1));
        achievements.put(Color.GREEN, new Point(2, 2));
        achievements.put(Color.BLUE, new Point(3, 3));
        achievements.put(Color.LIGHTBLUE, new Point(1, 2));
        achievements.put(Color.YELLOW, new Point(1, 3));
        achievements.put(Color.WHITE, new Point(2, 1));
        points = new Stack<>();
        for (int i = 6; i > 0; i--) {
            points.push(i);
        }
        personalGoal = new PersonalGoal(player, achievements, points, "1");
    }

    @Test
    public void testCalculateScore() {
        Shelf shelf = player.getShelf();
        shelf.setTileAtLocation(new Point(1, 1), new ItemTile(Color.PINK));
        shelf.setTileAtLocation(new Point(2, 2), new ItemTile(Color.GREEN));
        int score = personalGoal.calculateScore();
        assertEquals(3, score);
    }

    @Test
    public void testCalculateScoreWithPerfectScore() {
        Shelf shelf = player.getShelf();
        shelf.setTileAtLocation(new Point(1, 1), new ItemTile(Color.PINK));
        shelf.setTileAtLocation(new Point(2, 2), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(3, 3), new ItemTile(Color.BLUE));
        shelf.setTileAtLocation(new Point(1, 2), new ItemTile(Color.LIGHTBLUE));
        shelf.setTileAtLocation(new Point(1, 3), new ItemTile(Color.YELLOW));
        shelf.setTileAtLocation(new Point(2, 1), new ItemTile(Color.WHITE));
        int score = personalGoal.calculateScore();
        assertEquals(21, score);
    }

    @Test
    public void testCalculateScoreWithIncompleteGoals() {
        Shelf shelf = player.getShelf();
        shelf.setTileAtLocation(new Point(0, 1), new ItemTile(Color.PINK));
        shelf.setTileAtLocation(new Point(0, 2), new ItemTile(Color.LIGHTBLUE));
        shelf.setTileAtLocation(new Point(2, 2), new ItemTile(Color.BLUE));
        int score = personalGoal.calculateScore();
        assertEquals(0, score);
    }

    @Test
    public void testGetAchievements() {
        HashMap<Color, Point> achievementsResult = personalGoal.getAchievements();
        assertEquals(achievements, achievementsResult);
    }

    @Test
    public void testGetPointStack() {
        Stack<Integer> pointsResult = personalGoal.getPointStack();
        assertEquals(points, pointsResult);
    }
}
