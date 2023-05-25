package it.polimi.ingsw.View.GUI.SceneController.Utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShelfMemory {
    private static ImageView[][] imagesInMyShelf = new ImageView[6][5];

    public static void put(ImageView imageView, int row, int col) {
        imagesInMyShelf[row][col] = imageView;
    }

    public static ImageView get(int row, int col) {
        return imagesInMyShelf[row][col];
    }

    public static void setImage(Image image, int row, int col) {
        imagesInMyShelf[row][col].setImage(image);
    }

    public static void reset() {
        imagesInMyShelf = new ImageView[6][5];
    }
}
