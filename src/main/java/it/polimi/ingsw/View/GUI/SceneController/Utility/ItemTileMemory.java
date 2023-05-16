package it.polimi.ingsw.View.GUI.SceneController.Utility;

import it.polimi.ingsw.model.tiles.ItemTile;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ItemTileMemory {
    private static final Map<Integer, ItemTile> ID_TO_ITEM_TILE = new HashMap<>();
    private static final Map<Integer, Point> ID_TO_POSITION = new HashMap<>();
    private static final Map<Integer, Image> TILE_TO_IMAGE = new HashMap<>();


    public static void put(int id, ItemTile itemTile) {
        ID_TO_ITEM_TILE.put(id, itemTile);
    }

    public static void put(int id, Point position) {
        ID_TO_POSITION.put(id, position);
    }

    public static void put(int id, Image image) {
        TILE_TO_IMAGE.put(id, image);
    }

    public static void put(int id, ItemTile itemTile, Point position) {
        ID_TO_ITEM_TILE.put(id, itemTile);
        ID_TO_POSITION.put(id, position);
    }

    public static void put(int id, ItemTile itemTile, Point position, Image image) {
        ID_TO_ITEM_TILE.put(id, itemTile);
        ID_TO_POSITION.put(id, position);
        TILE_TO_IMAGE.put(id, image);
    }

    public static Point getPoint(int id) {
        return ID_TO_POSITION.get(id);
    }

    public static ItemTile getTile(int id) {
        return ID_TO_ITEM_TILE.get(id);
    }

    public static Image getImage(int id) {
        return TILE_TO_IMAGE.get(id);
    }

    public static boolean contains(int id) {
        return ID_TO_ITEM_TILE.containsKey(id);
    }

}
