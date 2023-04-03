package it.polimi.ingsw.model.cards.personalGoals;


import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class tests {@link PersonalGoal}'s methods
 */
public class PersonalGoalsTest {
/*
    @Test
    public void pointStackTest() throws TooManyPlayersException, IOException, ParseException, NoPlayersException {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player());
        PersonalCardDealer.getCards(players);

        PersonalGoal personalGoal = players.get(0).getPersonalGoal();
        Stack<Integer> pointStack = new ReadFromJSONFile().getPointStack();
        assertEquals(pointStack, personalGoal.getPointStack()); // full Stack
    }

    @Test
    public void scoreCalculationTest1() throws IOException, ParseException {
        Player player = new Player();
        HashMap<Color, Point> achievements = new ReadFromJSONFile().getPersonalGoalsData("1.json");
        Stack<Integer> pointStack = new ReadFromJSONFile().getPointStack();
        PersonalGoal personalGoal = new PersonalGoal(player, achievements, pointStack);

        //TODO write test afeter shelf logic is fixed

        assertEquals(pointStack.pop(), personalGoal.calculateScore()); // 1

        assertEquals(pointStack.pop(), personalGoal.calculateScore()); // 2

        assertEquals(pointStack.pop(), personalGoal.calculateScore()); // 4

        assertEquals(pointStack.pop(), personalGoal.calculateScore()); // 6

        assertEquals(pointStack.pop(), personalGoal.calculateScore()); // 9

        assertEquals(pointStack.pop(), personalGoal.calculateScore()); // 12
    }
    */

    private PersonalGoal personalGoal;
    private Player player;
    private HashMap<Color, Point> achievements;
    private Stack<Integer> points;

    @BeforeEach
    public void setUp() {
        player = new Player();
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
        personalGoal = new PersonalGoal(player, achievements, points);
    }

    @Test
    public void testCalculateScore() {
        Shelf shelf = player.getShelf();
        shelf.setTileAtLocation(new Point(1, 1), new ItemTile(Color.PINK));
        shelf.setTileAtLocation(new Point(2, 2), new ItemTile(Color.GREEN));
        int score = personalGoal.calculateScore();
        Assertions.assertEquals(3, score);
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
        Assertions.assertEquals(21, score);
    }

    @Test
    public void testCalculateScoreWithIncompleteGoals() {
        Shelf shelf = player.getShelf();
        shelf.setTileAtLocation(new Point(0, 1), new ItemTile(Color.PINK));
        shelf.setTileAtLocation(new Point(0, 2), new ItemTile(Color.LIGHTBLUE));
        shelf.setTileAtLocation(new Point(2, 2), new ItemTile(Color.BLUE));
        int score = personalGoal.calculateScore();
        Assertions.assertEquals(0, score);
    }

    @Test
    public void testGetAchievements() {
        HashMap<Color, Point> achievementsResult = personalGoal.getAchievements();
        Assertions.assertEquals(achievements, achievementsResult);
    }

    @Test
    public void testGetPointStack() {
        Stack<Integer> pointsResult = personalGoal.getPointStack();
        Assertions.assertEquals(points, pointsResult);
    }
}
