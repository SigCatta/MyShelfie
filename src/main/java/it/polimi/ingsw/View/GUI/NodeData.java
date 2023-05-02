package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.model.tiles.Color;
import javafx.scene.image.ImageView;

public class NodeData {
    private String url;
    private Color color;
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
