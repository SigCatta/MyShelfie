package it.polimi.ingsw.Controller.Client.Mappers;

import it.polimi.ingsw.network.client.SocketClient;

import java.util.HashMap;
import java.util.Stack;

public class InsertTilesMapper implements ClientMappable {
    @Override
    public void map(Stack<String> strings) {
        HashMap<String, String> commandMap = new HashMap<>();
        if (strings.size() != 5) return; //TODO should never happen

        commandMap.put("COLUMN", strings.pop());
        commandMap.put("TILE_INDEX", strings.pop());
        commandMap.put("GAMEID", strings.pop());
        commandMap.put("NICKNAME", strings.pop());
        commandMap.put("COMMAND", strings.pop());

        SocketClient.getInstance().sendCommand(commandMap);
    }
}
