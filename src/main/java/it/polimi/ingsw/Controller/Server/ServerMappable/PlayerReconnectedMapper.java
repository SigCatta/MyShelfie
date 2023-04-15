package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;

public class PlayerReconnectedMapper extends ServerMappable {

    @Override
    public void map(Object o) {
        HashMap<String, String> map = new HashMap<>();
        if (!(o instanceof Player)) return; // should never happen
        Player player = (Player) o;

        map.put("COMMAND", "PLAYER_RECONNECTED");
        map.put("NICKNAME", player.getNickname());
        //TODO send the notify to the clients
    }
}
