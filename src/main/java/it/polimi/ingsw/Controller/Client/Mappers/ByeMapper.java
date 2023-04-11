package it.polimi.ingsw.Controller.Client.Mappers;

import java.util.HashMap;
import java.util.Stack;

public class ByeMapper implements Mappable{
    @Override
    public HashMap<String, String> map(Stack<String> strings) {
        HashMap<String, String> commandMap = new HashMap<>();

        commandMap.put("NICKNAME", strings.pop());
        commandMap.put("GAMEID", strings.pop()); //TODO get the game id
        commandMap.put("COMMAND", "BYE");

        //TODO send map to network
        return commandMap;
    }
}
