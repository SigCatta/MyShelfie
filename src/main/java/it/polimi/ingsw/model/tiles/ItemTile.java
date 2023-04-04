package it.polimi.ingsw.model.tiles;

public class ItemTile {
    private final Color COLOR;

    /**
     * Constructs a new item tile given the color
     *
     * @param color the color of the tile
     */
    public ItemTile(Color color) {
        this.COLOR = color;
    }

    /**
     * @return the color of the tile
     */
    public Color getColor() {
        return COLOR;
    }

}