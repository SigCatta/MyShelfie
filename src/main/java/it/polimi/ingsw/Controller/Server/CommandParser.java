package it.polimi.ingsw.Controller.Server;

import it.polimi.ingsw.Controller.Server.Executor.*;

import java.util.HashMap;

/**
 * translates the messages into an action executor
 */
public class CommandParser {
    private HashMap<String, Executor> commandTranslator;

    public CommandParser(){
        commandTranslator = new HashMap<>();
        commandTranslator.put("CHAT", new ChatExecutor());
        commandTranslator.put("CAN_I_PLAY", new CanIPlayExecutor());
        commandTranslator.put("INSERT_TILES", new InsertTilesExecutor());
        commandTranslator.put("NEW_GAME", new NewGameExecutor());
        commandTranslator.put("PICK_UP_TILES", new PickupTilesExecutor());
    }

    /**
     * calls the right executor based on the string with the "COMMAND" key
     * @param data object received from the network
     */
    public void parse(HashMap<String, String> data){
        String command = data.get("COMMAND");

        if(command == null) return; //should never reach

        commandTranslator.get(command).execute(data);
    }
}
