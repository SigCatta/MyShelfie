package it.polimi.ingsw.View.CLI.InputStates.readers;

import it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors.*;

/**
 * Selects which command corresponds to the player's input
 */
public class CommandExecutorFactory {
    public static CommandExecutor getCommand(String command) {
        switch (command) {
            case "pickup_tiles":
                return new PickupTilesCE(); //TODO
            case "insert_tiles":
                return new InsertTilesCE(); //TODO
            case "chat":
                return new ChatCE();
            case "disconnect":
                return new DisconnectCE(); //TODO
            case "common_goals":
                return new CommonGoalCE();
            case "personal_goal":
                return new PersonalGoalCE();
            case "shelves":
                return new ShelvesCE();
        }
        return new InvalidCE();
    }
}