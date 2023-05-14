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
     * updates the virtual view with the item tiles given with the reference
     *
     * @param reference the way the grid must appear after the update
     */
    public static void updateItemTileGrid(GridPane gridPane, ItemTile[][] reference) {
        for (int row = 0; row < reference.length; row++) {
            for (int col = 0; col < reference[row].length; col++) {

                if (reference[row][col] == null) continue;

                int id = reference[row][col].getId(); //the id of the item tile
                if (!ItemTileMemory.contains(id)) {
                    ItemTileMemory.put(id, reference[row][col], new Point(row, col), createImage(reference[row][col]));
                }

                ImageView imageView = (ImageView) getNodeFromGridPane(gridPane, row, col);
                if (imageView == null) continue;

                imageView.setUserData(id); //set the id of the item tile to the image

                imageView.setImage(ItemTileMemory.getImage(id));
            }
        }
    }
}
