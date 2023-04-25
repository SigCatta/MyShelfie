package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.network.server.Server;

import java.util.HashMap;

public abstract class ServerMappable {
    Server server = Server.getInstance();
    abstract void map(Object o);

    /**
     * the map is built from the object board using this standard:
     * <p>
     * {ROW1:RED,GREEN,WHITE,YELLOW,...}
     * {ROW2:RED,WHITE,WHITE,WHITE,...}
     * ...
     * <p>
     * this map is then sent to the server socket and to the network
     */
    protected HashMap<String, String> encodeItemTilesGrid(ItemTile[][] tilesGrid) {
        HashMap<String, String> map = new HashMap<>();

        for (int row = 0; row < tilesGrid.length; row++) {

            StringBuilder rowString = new StringBuilder();

            for (int col = 0; col < tilesGrid.length; col++) {
                rowString.append(tilesGrid[row][col].getColor().toString()).append(",");
            }

            map.put("ROW" + row, rowString.toString());
        }

        return map;
    }
}
