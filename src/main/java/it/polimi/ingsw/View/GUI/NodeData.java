package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.model.tiles.Color;
import javafx.scene.image.ImageView;

/**
 * helper class that is used to save useful information about an ItemTile that has been picked up and needs to be put into the shelf
 */
public class NodeData {
    /**
     * the url of the image representing the tile
     */
    private String url;
    /**
     * the color of the the tile
     */
    private Color color;
    /**
     * the I,ageView containing the image representing the tile
     */
    private ImageView imageView;

    public NodeData(String url, Color color, ImageView imageView) {
        this.url = url;
        this.color = color;
        this.imageView = imageView;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
