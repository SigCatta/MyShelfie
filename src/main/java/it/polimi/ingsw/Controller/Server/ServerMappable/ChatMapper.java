package it.polimi.ingsw.Controller.Server.ServerMappable;

import java.util.HashMap;

public class ChatMapper extends ServerMappable {
    @Override
    public void map(Object o) {
        HashMap<String, String> map = new HashMap<>();
        if (!(o instanceof HashMap<?, ?>)) return; // should never happen
        HashMap<?, ?> objMap = (HashMap<?, ?>) o;

        map.put("GAMEID", (String) objMap.get("GAMEID"));
        map.put("NICKNAME", (String) objMap.get("NICKNAME"));
        map.put("MESSAGE", (String) objMap.get("MESSAGE"));
    }
}
