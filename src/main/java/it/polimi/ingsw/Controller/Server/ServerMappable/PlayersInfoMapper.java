package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayersInfoMapper extends ServerMappable {
    @Override
    public void map(Object o) {
        HashMap<String, String> map = new HashMap<>();
        if (!(o instanceof Game)) return; // should never happen
        Game game = (Game) o;
        ArrayList<Player> players = game.getPlayers();

        map.put("COMMAND", "PLAYERS_INFO");
        for (int i = 0; i < players.size(); i++){
           map.put("PLAYER" + i, players.get(i).getNickname());
        }
        map.put("GAMEID", Integer.toString(game.getGameID()));

    }
}
