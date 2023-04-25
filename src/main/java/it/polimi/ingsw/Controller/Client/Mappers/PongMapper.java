package it.polimi.ingsw.Controller.Client.Mappers;

import java.util.HashMap;
import java.util.Stack;

public class PongMapper implements ClientMappable{
    @Override
    public void map(Stack<String> strings) {

    }

    public static HashMap<String, String> getMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("COMMAND", "PONG");
        return map;
    }
}
