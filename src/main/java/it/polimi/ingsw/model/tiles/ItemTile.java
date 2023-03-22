package it.polimi.ingsw.model.tiles;

public class ItemTile {
    private final Color COLOR;

    public ItemTile(Color color){
        this.COLOR = color;
        //request for the tile image from database
    }

    public Color getColor() {
        return COLOR;
    }

}