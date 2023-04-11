package it.polimi.ingsw.model.GameState;

import it.polimi.ingsw.JSONReader.GameStateDataReader;

import java.util.Set;

public class EndGameState implements GameState{

    /**
     * static so it has to be set just once
     */
    private static Set<String> possibleCommands;

    public EndGameState(){
        if(possibleCommands == null){
            possibleCommands = GameStateDataReader.getSetFromJSON("EndGameCommands.json");
        }
    }

    @Override
    public boolean isCommandPossible(String command) {
        return possibleCommands.contains(command);
    }
}
