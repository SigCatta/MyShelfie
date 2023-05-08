package it.polimi.ingsw.View.GUI;

import java.util.ArrayList;
import java.util.List;

public class TilesSelectedCointainer {
    static List<NodeData> tilesSelected = new ArrayList<>();

    public static void setTilesSelected(List<NodeData> tiles) {
        tilesSelected = new ArrayList<>(tiles);
    }

    public static List<NodeData> getTilesSelected() {
        return tilesSelected;
    }
}
