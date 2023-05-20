package it.polimi.ingsw.View.GUI.SceneController.Utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShelfMemory {
    private static ImageView[][] imagesInMyShelf = new ImageView[6][5];
    private static ImageView[][] imagesInOtherShelf = new ImageView[6][5];

    /**
     * @param selector if selector == 0, then the imageView is to be inserted into imagesInMyShelf,
     *                 else into imagesInOtherShelf
     */
    public static void put(ImageView imageView, int row, int col, int selector) {
        if (selector == 0)
            imagesInMyShelf[row][col] = imageView;
        else imagesInOtherShelf[row][col] = imageView;
    }

    /**
     * @param selector if selector == 0, then the imageView is to be got from imagesInMyShelf,
     *                 else from imagesInOtherShelf
     */
    public static ImageView get(int row, int col, int selector) {
        if (selector == 0)
            return imagesInMyShelf[row][col];
        else return imagesInOtherShelf[row][col];
    }

    /**
     * @param selector if selector == 0, then the imageView is to be set into imagesInMyShelf,
     *                 else into imagesInOtherShelf
     */
    public static void setImage(Image image, int row, int col, int selector) {
        if (selector == 0)
            imagesInMyShelf[row][col].setImage(image);
        else imagesInOtherShelf[row][col].setImage(image);
    }

    public static void reset(int selector) {
        if (selector == 0)
            imagesInMyShelf = new ImageView[6][5];
        else imagesInOtherShelf = new ImageView[6][5];
    }
}
