package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Enum.Color;
import javafx.scene.image.ImageView;

import java.awt.*;

/**
 * helper class that is used to save useful information about an ItemTile that has been picked up and needs to be put into the shelf
 */
public class NodeData {
    /**
     * the url of the image representing the tile
     */
    private String url;
    /**
     * the color of the tile
     */
    private Color color;
    /**
     * the ImageView containing the image representing the tile
     */
    private ImageView imageView;
    /**
     * the position of the board in which the tile is place
     */
    private Point position;

    public NodeData(String url, Color color, ImageView imageView, Point position) {
        this.url = url;
        this.color = color;
        this.imageView = imageView;
        this.position = position;
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

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
