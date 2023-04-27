package it.polimi.ingsw.Controller.Server.ServerMappable;

import java.util.HashMap;

public class ErrorMapper extends ServerMappable{
    @Override
    public void map(Object o) {
        HashMap<String, String> map = new HashMap<>();
        if (!(o instanceof String)) return; // should never happen
        String errorMessage = (String) o;

        map.put("COMMAND", "ERROR");
        map.put("ERROR", errorMessage);
        map.put("NICKNAME", errorMessage);
        map.put("GAMEID", errorMessage);
    }
}