package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;

public class PlayerDownMapper extends ServerMappable {
    @Override
    public void map(Object o) {
        HashMap<String, String> map = new HashMap<>();
        if (!(o instanceof Player)) return; // should never happen
        Player player = (Player) o;

        map.put("NICKNAME", player.getNickname());
        //TODO need a way to get GAMEID ~ make serverSocket look for it in the collection of players ?
    }
}
