package it.polimi.ingsw.model.tiles;

public class ItemTile {
    private final Color COLOR;

    /**
     * Contructs a new item tile given the color
     *
     * @param color the color of the tile
     * @author Luca Cattani
     */
    public ItemTile(Color color) {
        this.COLOR = color;
        //request for the tile image from database
    }

    /**
     * @return the color of the tile
     * @author Luca Cattani
     */
    public Color getColor() {
        return COLOR;
    }

}