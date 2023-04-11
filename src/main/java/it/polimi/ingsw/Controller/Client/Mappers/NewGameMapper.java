package it.polimi.ingsw.Controller.Client.Mappers;

import java.util.HashMap;
import java.util.Stack;

public class NewGameMapper implements ClientMappable {
    @Override
    public HashMap<String, String> map(Stack<String> strings) {
        HashMap<String, String> commandMap = new HashMap<>();
        if (strings.size() != 3) return null; //TODO should never hannpen

        commandMap.put("NUM_OF_PLAYERS", strings.pop());
        commandMap.put("NICKNAME", strings.pop());
        commandMap.put("COMMAND", strings.pop());

        return commandMap;
    }
}
