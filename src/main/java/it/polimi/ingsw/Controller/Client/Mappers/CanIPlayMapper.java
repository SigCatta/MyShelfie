package it.polimi.ingsw.Controller.Client.Mappers;

import java.util.HashMap;
import java.util.Stack;

public class CanIPlayMapper implements Mappable{
    @Override
    public HashMap<String, String> map(Stack<String> strings) { //CANIPLAY GAMEID NICKNAME
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("COMMAND", strings.pop());
        commandMap.put("GAMEID", strings.pop());
        commandMap.put("NICKNAME", strings.pop());

        return commandMap;
    }
}
