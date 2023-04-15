package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashMap;
import java.util.List;

public class HeldTilesMapper extends ServerMappable{

    @Override
    public void map(Object o) {
        HashMap<String, String> map = new HashMap<>();
        if (!(o instanceof TilesGetter)) return; // should never happen
        TilesGetter tilesGetter = (TilesGetter) o;
        List<ItemTile> tilesToBeInserted = tilesGetter.getTilesToBeInserted();

        map.put("COMMAND", "HELD_TILES");
        int tileNumber = 1;
        for(ItemTile itemTile : tilesToBeInserted){
            map.put("TILE" + tileNumber, itemTile.getColor().toString());
        }

        //TODO send the map to the clients
    }
}
