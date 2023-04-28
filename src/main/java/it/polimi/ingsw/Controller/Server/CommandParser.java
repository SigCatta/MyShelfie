package it.polimi.ingsw.Controller.Server;

import it.polimi.ingsw.Controller.Commands.CommandMapKey;
import it.polimi.ingsw.Controller.Commands.CommandType;
import it.polimi.ingsw.Controller.Server.Executor.*;
import it.polimi.ingsw.model.Game;

import java.util.HashMap;

/**
 * translates the messages into an action executor
 * it is associate to only one game
 */
public class CommandParser {
    private HashMap<String, Executor> commandTranslator;

    /**
     * game associated with the command parser
     */
    private Game game;

    /**
     * it is possible to choose between creating the command parser
     * that is already linked with its game or assign it later
     */
    public CommandParser(Game game){
        this.game = game;

        commandInit();
    }

    public CommandParser(){
        commandInit();
    }

    private void commandInit(){
        commandTranslator = new HashMap<>();
        commandTranslator.put(String.valueOf(CommandType.CHAT), new ChatExecutor(game));
        commandTranslator.put(String.valueOf(CommandType.CAN_I_PLAY), new CanIPlayExecutor(game));
        commandTranslator.put(String.valueOf(CommandType.INSERT_TILES), new InsertTilesExecutor(game));
        commandTranslator.put(String.valueOf(CommandType.NEW_GAME), new NewGameExecutor(game));
        commandTranslator.put(String.valueOf(CommandType.PICK_UP_TILES), new PickupTilesExecutor(game));
        commandTranslator.put(String.valueOf(CommandType.DISCONNECT_PLAYER), new DisconnectPlayerExecutor(game));
    }

    /**
     * calls the right executor based on the string with the "COMMAND" key
     * @param data object received from the network
     */
    public void parse(HashMap<String, String> data){
        String command = data.get(String.valueOf(CommandMapKey.COMMAND));

        if(command == null) return; //should never reach

        commandTranslator.get(command).execute(data);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
