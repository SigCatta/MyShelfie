package it.polimi.ingsw.model.tiles;

import it.polimi.ingsw.Enum.Color;

import java.io.Serializable;

/**
 * A class representing a tile
 */
public class ItemTile implements Serializable {
    private final Color COLOR;
    /**
     * used to associate an image to a card
     * and make it in a way that it does not change
     * throughout the game
     */
    private final int id;

    /**
     * Constructs a new item tile given the color
     *
     * @param color the color of the tile
     */
    public ItemTile(Color color) {
        this.COLOR = color;
        id = (int) (Math.random() * Integer.MAX_VALUE);
    }

    public int getId() {
        return id;
    }

    /**
     * @return the color of the tile
     */
    public Color getColor() {
        return COLOR;
    }


}