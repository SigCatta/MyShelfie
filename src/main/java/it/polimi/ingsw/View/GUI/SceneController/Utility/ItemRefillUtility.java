package it.polimi.ingsw.View.GUI.SceneController.Utility;

import it.polimi.ingsw.model.tiles.ItemTile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class ItemRefillUtility {

    private static final String ITEM_TILES_PACKAGE = ItemRefillUtility.class.getClassLoader().getResource("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/item_tiles/").toString();


    public static Image createImage(ItemTile tile) {

        int imageNumber = (int) (Math.random() * 3) + 1;

        String tilePath = ITEM_TILES_PACKAGE + tile.getColor() + "/1." + imageNumber + ".png";

        return new Image(tilePath);
    }


    public static Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (node == null) continue;
            Integer c = GridPane.getColumnIndex(node);
            Integer r = GridPane.getRowIndex(node);
            if (c == null || r == null) continue;
            if (c == col && r == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * updates the board view with the item tiles given with the reference
     *
     * @param reference the way the grid must appear after the update
     */
    public static void updateBoardGrid(ItemTile[][] reference) {
        for (int row = 0; row < reference.length; row++) {
            for (int col = 0; col < reference[row].length; col++) {

                ImageView imageView = BoardMemory.get(row, col);
                if(imageView == null) continue;

                if (reference[row][col] == null){
                    BoardMemory.removeImage(row, col);
                    continue;
                }

                int id = reference[row][col].getId(); //the id of the item tile

                if (!ItemTileMemory.contains(id)) {
                    ItemTileMemory.put(id, reference[row][col], new Point(row, col), createImage(reference[row][col]));
                }

                BoardMemory.get(row, col).setUserData(id);
                imageView.setImage(ItemTileMemory.getImage(id));
                imageView.setOpacity(1);
            }
        }
    }


    /**
     * since the shelf is a write-only type of object this method uses this property
     * to optimize the update. It checks each column starting from above, and it stops
     * inserting if the tile is already shown in the shelf.
     *
     * @param shelf     to be updated
     * @param reference the correct shelf to show
     */
    public static void updateShelfGrid(GridPane shelf, ItemTile[][] reference) {
        for (int col = 0; col < reference[0].length; col++) {
            for (int row = reference.length - 1; row >= 0; row--) {

                if (reference[row][col] == null) break;

                int id = reference[row][col].getId(); //the id of the item tile

                if (ShelfMemory.get(row, col, 0).getImage() != null)
                    continue;  //if the tile is memorized this means it is already in the shelf

                Image image = ItemTileMemory.getImage(id);

                ShelfMemory.setImage(image, row, col, 0);
            }
        }
    }

    public static void updateOtherShelfGrid(ItemTile[][] reference) {
        for (int col = 0; col < reference[0].length; col++) {
            for (int row = reference.length - 1; row >= 0; row--) {
                if (reference[row][col] == null) break;

                int id = reference[row][col].getId(); //the id of the item tile

                Image image = ItemTileMemory.getImage(id);

                ShelfMemory.setImage(image, row, col, 1);
            }
        }
    }
}
