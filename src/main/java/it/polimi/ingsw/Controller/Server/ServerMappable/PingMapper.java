package it.polimi.ingsw.Controller.Server.ServerMappable;

import java.util.HashMap;

public class PingMapper extends ServerMappable{
    @Override
    void map(Object o) {

    }

    public static HashMap<String, String> getMap(){
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("NICKNAME", "SERVER");
        commandMap.put("COMMAND", "PING");

        return commandMap;
    }
}
