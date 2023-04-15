package it.polimi.ingsw.Controller.Client;

import java.util.HashMap;

public class ClientCommandParser {
    //private HashMap<String, Updater> commandTranslator;

    public ClientCommandParser(){
        //commandTranslator = new HashMap<>();

        //NOTE: the updater class is a class like executor with the difference that it updates the view

        //commandTranslator.put("CHAT", -- Updater class --);
        //commandTranslator.put("ERROR", -- Updater class --);
        //commandTranslator.put("NEW_GAME_OK", -- Updater class --);
        //commandTranslator.put("PLAYER_DOWN", -- Updater class --);
        //commandTranslator.put("SEND_BOARD", -- Updater class --);
        //commandTranslator.put("SEND_PLAYER_SCORE", -- Updater class --);
        //commandTranslator.put("SEND_SHELF", -- Updater class --);

        //commandTranslator.put("SEND_PLAYER", -- Updater class --); TODO CREATE THE MAPPER IN SERVER

    }

    /**
     * calls the right executor based on the string with the "COMMAND" key
     * @param data object received from the network
     */
    public void parse(HashMap<String, String> data){
        String command = data.get("COMMAND");

        if(command == null) return; //should never reach

        //commandTranslator.get(command).update(data);
    }
}
