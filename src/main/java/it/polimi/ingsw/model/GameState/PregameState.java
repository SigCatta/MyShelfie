package it.polimi.ingsw.model.GameState;

import it.polimi.ingsw.JSONReader.GameStateDataReader;

import java.util.Set;

public class PregameState implements GameState{

    private static Set<String> possibleCommands;

    public PregameState(){
        if(possibleCommands == null){
            possibleCommands = GameStateDataReader.getSetFromJSON("PregameCommands.json");
        }
    }

    @Override
    public boolean isCommandPossible(String command) {
        return possibleCommands.contains(command);
    }
}
