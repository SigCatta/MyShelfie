package it.polimi.ingsw.View.GUI.SceneController.Utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShelfMemory {
    private static ImageView[][] imagesInShelf = new ImageView[6][5];

    public static void put(ImageView imageView, int row, int col) {
        imagesInShelf[row][col] = imageView;
    }

    public static ImageView get(int row, int col) {
        return imagesInShelf[row][col];
    }

    public static void setImage(Image image, int row, int col) {
        imagesInShelf[row][col].setImage(image);
    }

    public static void reset() {
        imagesInShelf = new ImageView[6][5];
    }
}
