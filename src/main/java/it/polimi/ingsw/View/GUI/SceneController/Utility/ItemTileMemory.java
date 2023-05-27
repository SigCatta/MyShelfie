package it.polimi.ingsw.View.GUI.SceneController.Utility;

import it.polimi.ingsw.model.tiles.ItemTile;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The ItemTileMemory class is responsible for storing and retrieving information related to item tiles in the GUI.
 */
public class ItemTileMemory {
    private static final Map<Integer, ItemTile> ID_TO_ITEM_TILE = new HashMap<>();
    private static final Map<Integer, Point> ID_TO_POSITION = new HashMap<>();
    private static final Map<Integer, Image> TILE_TO_IMAGE = new HashMap<>();

    /**
     * Associates the given item tile with the specified ID.
     *
     * @param id       the ID of the item tile
     * @param itemTile the item tile to be associated
     */
    public static void put(int id, ItemTile itemTile) {
        ID_TO_ITEM_TILE.put(id, itemTile);
    }

    /**
     * Associates the given position with the specified ID.
     *
     * @param id       the ID of the item tile
     * @param position the position to be associated
     */
    public static void put(int id, Point position) {
        ID_TO_POSITION.put(id, position);
    }

    /**
     * Associates the given image with the specified ID.
     *
     * @param id    the ID of the item tile
     * @param image the image to be associated
     */
    public static void put(int id, Image image) {
        TILE_TO_IMAGE.put(id, image);
    }

    /**
     * Associates the given item tile and position with the specified ID.
     *
     * @param id         the ID of the item tile
     * @param itemTile   the item tile to be associated
     * @param position   the position to be associated
     */
    public static void put(int id, ItemTile itemTile, Point position) {
        ID_TO_ITEM_TILE.put(id, itemTile);
        ID_TO_POSITION.put(id, position);
    }

    /**
     * Associates the given item tile, position, and image with the specified ID.
     *
     * @param id         the ID of the item tile
     * @param itemTile   the item tile to be associated
     * @param position   the position to be associated
     * @param image      the image to be associated
     */
    public static void put(int id, ItemTile itemTile, Point position, Image image) {
        ID_TO_ITEM_TILE.put(id, itemTile);
        ID_TO_POSITION.put(id, position);
        TILE_TO_IMAGE.put(id, image);
    }

    /**
     * Retrieves the position associated with the specified ID.
     *
     * @param id the ID of the item tile
     * @return the position associated with the ID
     */
    public static Point getPoint(int id) {
        return ID_TO_POSITION.get(id);
    }

    /**
     * Retrieves the item tile associated with the specified ID.
     *
     * @param id the ID of the item tile
     * @return the item tile associated with the ID
     */
    public static ItemTile getTile(int id) {
        return ID_TO_ITEM_TILE.get(id);
    }

    /**
     * Retrieves the image associated with the specified ID.
     *
     * @param id the ID of the item tile
     * @return the image associated with the ID
     */
    public static Image getImage(int id) {
        return TILE_TO_IMAGE.get(id);
    }

    /**
     * Checks if the specified ID is associated with an item tile.
     *
     * @param id the ID to be checked
     * @return {@code true} if the ID is associated with an item tile, {@code false} otherwise
     */
    public static boolean contains(int id) {
        return ID_TO_ITEM_TILE.containsKey(id);
    }
}