package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashMap;

public class SendShelfMapper extends ServerMappable {
    /**
     * the map is built from the object board using this standard:
     * <p>
     * {ROW1:RED,GREEN,WHITE,YELLOW,...}
     * {ROW2:RED,WHITE,WHITE,WHITE,...}
     * ...
     * <p>
     * this map is then sent to the server socket and to the network
     */
    @Override
    public void map(Object o) {
        if (!(o instanceof Game)) return;//error

        Game game = (Game) o;
        Player activePlayer = game.getActivePlayer();
        Shelf shelf = activePlayer.getShelf();

        ItemTile[][] shelfGrid = shelf.getShelfGrid();

        HashMap<String, String> map = encodeItemTilesGrid(shelfGrid);
        map.put("COMMAND", "SEND_SHELF");
        map.put("NICKNAME", activePlayer.getNickname());
        map.put("GAMEID", String.valueOf(game.getGameID()));

        //TODO send to the server socket

    }
}
