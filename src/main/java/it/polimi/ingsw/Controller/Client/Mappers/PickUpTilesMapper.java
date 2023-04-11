package it.polimi.ingsw.Controller.Client.Mappers;

import java.util.HashMap;
import java.util.Stack;

public class PickUpTilesMapper implements Mappable{

    /**
     * the Stack strings must arrive as follows:
     * Points on the board
     * nickname
     * gameId (Taken from the virtual model)
     */
    @Override
    public HashMap<String, String> map(Stack<String> strings) {
        HashMap<String, String> commandMap = new HashMap<>();

        int pointPosition = 1;
        while (!strings.empty()){
            commandMap.put("X"+pointPosition, strings.pop());
            commandMap.put("Y"+pointPosition, strings.pop());
            pointPosition++;
        }

        commandMap.put("NICKNAME", strings.pop());
        commandMap.put("GAMEID", strings.pop()); //TODO get the game id
        commandMap.put("COMMAND", "PICK_UP_TILES");

        //TODO send map to network
        return commandMap;
    }
}
