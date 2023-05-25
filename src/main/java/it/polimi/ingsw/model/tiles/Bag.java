package it.polimi.ingsw.model.tiles;

import it.polimi.ingsw.Enum.Color;

import java.util.HashMap;

public class Bag {

    private final int NUMBER_OF_COLORS;
    private final int TILES_PER_COLOR = 22;
    private int tilesLeft;
    private final HashMap<Color, Integer> colorNumber = new HashMap<>();

    public Bag() {

        NUMBER_OF_COLORS = Color.values().length;
        tilesLeft = NUMBER_OF_COLORS * TILES_PER_COLOR;

        for (Color color : Color.values()) {
            colorNumber.put(color, TILES_PER_COLOR);
        }

    }

    /**
     * Draws a single ItemTile from the bag
     *
     * @return a single ItemTile
     */
    public ItemTile drawTile() {

        Color randomColor = randomColor();
        if (randomColor == null) return null;

        return new ItemTile(randomColor);
    }


    /**
     * this method uses a random variable to get a random number from 0 to tilesLeft
     * partialCount is used to iterate through the map until the sum of the values
     * reaches the random variable, in that case it returns the current color
     *
     * @return random color
     */
    Color randomColor() {

        if (tilesLeft == 0) return null;

        double random = Math.random() * tilesLeft;
        tilesLeft--;


        int partialCount = 0;
        for (Color color : Color.values()) {
            partialCount += colorNumber.get(color);

            if (partialCount >= random) {
                colorNumber.replace(color, colorNumber.get(color) - 1);
                return color;
            }
        }
        return null;
    }
}
