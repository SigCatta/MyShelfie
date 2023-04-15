package it.polimi.ingsw.Controller.Client.Mappers;

import it.polimi.ingsw.network.client.SocketClient;

import java.util.HashMap;
import java.util.Stack;

public class PickUpTilesMapper implements ClientMappable {
    /**
     * the Stack strings must arrive as follows:
     * Points on the board
     * nickname
     * gameId (Taken from the virtual model)
     */
    @Override
    public void map(Stack<String> strings) {
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
        SocketClient.getInstance().sendCommand(commandMap);
    }
}
