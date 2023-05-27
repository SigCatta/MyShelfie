package it.polimi.ingsw.View.GUI.SceneController.Utility;

import javafx.scene.image.ImageView;

/**
 * this class is used to access a specific child of the board.
 * This way it is possible to access a place in the board with O(1)
 */
public class BoardMemory {
    private static final ImageView[][] imagesInBoard = new ImageView[9][9];

    /**
     * Puts the specified ImageView at the given position in the board memory.
     *
     * @param imageView the ImageView to be stored
     * @param row       the row index of the position
     * @param col       the column index of the position
     */
    public static void put(ImageView imageView, int row, int col) {
        imagesInBoard[row][col] = imageView;
    }

    /**
     * Retrieves the ImageView at the given position in the board memory.
     *
     * @param row the row index of the position
     * @param col the column index of the position
     * @return the ImageView at the specified position
     */
    public static ImageView get(int row, int col) {
        return imagesInBoard[row][col];
    }

    /**
     * Removes the image at the given position in the board memory by setting it to null.
     *
     * @param row the row index of the position
     * @param col the column index of the position
     */
    public static void removeImage(int row, int col) {
        imagesInBoard[row][col].setImage(null);
    }
}
