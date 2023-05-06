package it.polimi.ingsw.model.tiles;

import it.polimi.ingsw.Enum.Color;

import java.io.Serializable;

public class ItemTile implements Serializable {
    private final Color COLOR;

    /**
     * Constructs a new item tile given the color
     *
     * @param color the color of the tile
     */
    public ItemTile(Color color) {
        this.COLOR = color;
        //request for the tile image from database
    }

    /**
     * @return the color of the tile
     */
    public Color getColor() {
        return COLOR;
    }

}