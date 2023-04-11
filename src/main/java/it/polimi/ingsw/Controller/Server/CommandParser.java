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
        commandTranslator.put("chat", new ChatExecutor());
        commandTranslator.put("can_i_play", new CanIPlayExecutor());
        commandTranslator.put("insert_tiles", new InsertTIlesExecutor());
        commandTranslator.put("new_game", new NewGameExecutor());
        commandTranslator.put("pick_up", new PickupTilesExecutor());
    }

    /**
     * calls the right executor based on the string with the "instruction" key
     * @param data object received from the network
     */
    public void parse(HashMap<String, String> data){
        String instruction = data.get("instruction");

        if(instruction == null) return; //should never reach

        commandTranslator.get(instruction).execute(data);
    }
}
