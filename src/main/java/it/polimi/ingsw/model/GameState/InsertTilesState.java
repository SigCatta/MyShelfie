package it.polimi.ingsw.model.GameState;

import it.polimi.ingsw.JSONReader.GameStateDataReader;

import java.util.Set;

public class InsertTilesState implements GameState{
    private static Set<String> possibleCommands;

    public InsertTilesState(){
        if(possibleCommands == null){
            possibleCommands = GameStateDataReader.getSetFromJSON("InsertTilesCommands.json");
        }
    }

    @Override
    public boolean isCommandPossible(String command) {
        return possibleCommands.contains(command);
    }
}
