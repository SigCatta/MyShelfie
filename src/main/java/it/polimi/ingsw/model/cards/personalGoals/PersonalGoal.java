package it.polimi.ingsw.model.cards.personalGoals;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

/**
 * A class describing the personal goals a certain player has to complete to earn points,
 * it contains a map containing where the player needs to put a tile of a certain color to earn points
 */
public class PersonalGoal {
    private final HashMap<Color, Point> achievements;
    private final Player player;
    private final Stack<Integer> points;
    private final String cardNumber;

    /**
     * Constructs a new personal goal
     *
     * @param player       the player who drew the card
     * @param achievements the list of objectives that have to be completed
     * @param points       stack of points the player will get after completing objectives
     */
    PersonalGoal(Player player, HashMap<Color, Point> achievements, Stack<Integer> points, String cardNumber) {
        this.player = player;
        this.achievements = achievements;
        this.points = points;
        this.cardNumber = cardNumber;
    }

    /**
     * Checks if the player has completed any of the goals set by the personal card and returns the total earned
     *
     * @return the sum of points that the player earned from a personal goal card
     */

    public int calculateScore() {
        int score = 0;
        if (!points.isEmpty()) {
            for (Color color : Color.values()) {
                if (color == Color.EMPTY) continue;
                Point point = achievements.get(color);
                Shelf shelf = player.getShelf();
                ItemTile tileAtPoint = shelf.getTileAtLocation(point);
                if(tileAtPoint == null) continue;
                if (tileAtPoint.getColor() == color) {
                    score += getPoints();
                    achievements.remove(color);
                }
            }
        }
        return score;
    }

    /**
     * @return the number of points that the player gets because of completing the objective
     */

    private int getPoints() {
        return points.pop();
    }

    /**
     * @return a map of the goals that are not completed yet
     */
    public HashMap<Color, Point> getAchievements() {
        return achievements;
    }

    /**
     * @return a stack containing the points that the player can still collect from completing the remaining objectives from the personal card
     */

    public Stack<Integer> getPointStack() {
        return points;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}