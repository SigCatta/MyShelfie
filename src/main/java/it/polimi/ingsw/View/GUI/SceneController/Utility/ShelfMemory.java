package it.polimi.ingsw.View.GUI.SceneController.Utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ShelfMemory class is responsible for storing and retrieving information related to the player's shelf in the GUI.
 */
public class ShelfMemory {
    private static final ImageView[][] imagesInMyShelf = new ImageView[6][5];

    /**
     * Associates the given ImageView with the specified position in the player's shelf.
     *
     * @param imageView the ImageView to be associated
     * @param row       the row position in the shelf
     * @param col       the column position in the shelf
     */
    public static void put(ImageView imageView, int row, int col) {
        imagesInMyShelf[row][col] = imageView;
    }

    /**
     * Retrieves the ImageView associated with the specified position in the player's shelf.
     *
     * @param row the row position in the shelf
     * @param col the column position in the shelf
     * @return the ImageView associated with the position
     */
    public static ImageView get(int row, int col) {
        return imagesInMyShelf[row][col];
    }

    /**
     * Sets the specified image to the ImageView at the specified position in the player's shelf.
     *
     * @param image the image to be set
     * @param row   the row position in the shelf
     * @param col   the column position in the shelf
     */
    public static void setImage(Image image, int row, int col) {
        imagesInMyShelf[row][col].setImage(image);
    }
}

