package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;
import java.util.List;

public class SendPlayersScore extends ServerMappable{
    @Override
    void map(Object o) {

        if (! (o instanceof List<?> ))return; //error
        List<?> list = (List<?>) o;
        if (! (list.size() > 0 && list.get(0) instanceof Player) ) return; //error

        HashMap<String, String> map = new HashMap<>();

        map.put("COMMAND", "SEND_PLAYER_SCORE");

        for(Object objPlayer : list){
            Player player = (Player) objPlayer;
            map.put(player.getNickname(), String.valueOf(player.getScore()));
        }

        //TODO send to the server socket

    }
}
