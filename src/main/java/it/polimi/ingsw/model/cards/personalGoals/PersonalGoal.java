package it.polimi.ingsw.model.cards.personalGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.Point;
import java.util.HashMap;
import java.util.Stack;

public class PersonalGoal {
    private final HashMap<Color, Point> achievements;
    private final Player player;
    private final Stack<Integer> points;

    PersonalGoal(Player player, HashMap<Color, Point> achievements, Stack<Integer> points) {
        this.player = player;
        this.achievements = achievements;
        this.points = points;
    }

    public int calculateScore() {
        int score = 0;
        if (!points.isEmpty()) {
            for (Color color : Color.values()) {
                Point point = achievements.get(color);
                Shelf shelf = player.getShelf();
                ItemTile tileAtPoint = shelf.getTileAtLocation(point);
                if (tileAtPoint.getColor() == color) {
                    score += getPoints();
                    achievements.remove(color);
                }
            }
        }
        return score;
    }

    private int getPoints() {
        return points.pop();
    }

    public HashMap<Color, Point> getAchievements() {
        return achievements;
    }

    public Stack<Integer> getPointStack() {
        return points;
    }
}