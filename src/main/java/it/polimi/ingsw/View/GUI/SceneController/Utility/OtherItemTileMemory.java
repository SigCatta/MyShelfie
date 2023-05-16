package it.polimi.ingsw.View.GUI.SceneController.Utility;

import it.polimi.ingsw.model.tiles.ItemTile;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OtherItemTileMemory extends ItemTileMemory{
    private static final Map<Integer, ItemTile> ID_TO_ITEM_TILE = new HashMap<>();
    private static final Map<Integer, Point> ID_TO_POSITION = new HashMap<>();
    private static final Map<Integer, Image> TILE_TO_IMAGE = new HashMap<>();
}
