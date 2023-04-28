package it.polimi.ingsw.Controller.Client.Mappers;

import it.polimi.ingsw.Controller.Commands.CommandMapKey;
import it.polimi.ingsw.Controller.Commands.CommandType;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.HashMap;
import java.util.Stack;

public class ByeMapper implements ClientMappable {
    @Override
    public void map(Stack<String> strings) {
        HashMap<String, String> commandMap = new HashMap<>();

        commandMap.put(String.valueOf(CommandMapKey.NICKNAME), strings.pop());
        commandMap.put(String.valueOf(CommandMapKey.GAMEID), strings.pop()); //TODO get the game id
        commandMap.put(String.valueOf(CommandMapKey.COMMAND), String.valueOf(CommandType.BYE));

        SocketClient.getInstance().sendCommand(commandMap);
    }
}
