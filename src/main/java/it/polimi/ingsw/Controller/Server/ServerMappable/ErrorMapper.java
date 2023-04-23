package it.polimi.ingsw.Controller.Server.ServerMappable;

import java.util.HashMap;

public class ErrorMapper extends ServerMappable{
    @Override
    public void map(Object o) {
        HashMap<String, String> errorMessage = getMap(o);

        if(errorMessage == null) return;

        //TODO send the map
    }

    /**
     * @param o a string containing the error message
     * @return the map to be sent via socket
     */
    public static HashMap<String, String> getMap(Object o){
        HashMap<String, String> map = new HashMap<>();
        if (!(o instanceof String)) return null; // should never happen
        String errorMessage = (String) o;

        map.put("COMMAND", "ERROR");
        map.put("ERROR", errorMessage);
        map.put("NICKNAME", errorMessage);
        map.put("GAMEID", errorMessage);

        return map;
    }
}
