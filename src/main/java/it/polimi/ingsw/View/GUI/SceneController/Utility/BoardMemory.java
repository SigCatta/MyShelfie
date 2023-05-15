package it.polimi.ingsw.View.GUI.SceneController.Utility;

import javafx.scene.image.ImageView;

/**
 * this class is used to access a specific child of the board.
 * This way it is possible to access a place in the board with O(1)
 */
public class BoardMemory {
    private static ImageView[][] imagesInBoard = new ImageView[9][9];

    public static void put(ImageView imageView, int row, int col) {
        imagesInBoard[row][col] = imageView;
    }

    public static ImageView get(int row, int col) {
        return imagesInBoard[row][col];
    }
}
