package it.polimi.ingsw.Controller.Client.Mappers;

import it.polimi.ingsw.network.client.SocketClient;

import java.util.HashMap;
import java.util.Stack;

public class ByeMapper implements ClientMappable {
    @Override
    public void map(Stack<String> strings) {
        HashMap<String, String> commandMap = new HashMap<>();

        commandMap.put("NICKNAME", strings.pop());
        commandMap.put("GAMEID", strings.pop()); //TODO get the game id
        commandMap.put("COMMAND", "BYE");

        SocketClient.getInstance().sendCommand(commandMap);
    }
}
