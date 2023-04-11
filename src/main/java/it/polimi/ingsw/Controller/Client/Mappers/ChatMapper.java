package it.polimi.ingsw.Controller.Client.Mappers;

import java.util.HashMap;
import java.util.Stack;

public class ChatMapper implements Mappable{
    public HashMap<String, String> map(Stack<String> strings) { //CANIPLAY GAMEID NICKNAME
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("COMMAND", strings.pop());
        commandMap.put("GAMEID", strings.pop());
        commandMap.put("NICKNAME", strings.pop());

        commandMap.put("MESSAGE", strings.pop());

        //TODO send map to network
        return commandMap;
    }
}
