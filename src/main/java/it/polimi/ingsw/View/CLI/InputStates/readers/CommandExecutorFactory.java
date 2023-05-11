package it.polimi.ingsw.View.CLI.InputStates.readers;

import it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors.*;

/**
 * Selects which command corresponds to the player's input
 */
public class CommandExecutorFactory {
    public static CommandExecutor getCommand(String command) {
        switch (command) {
            case "pickup":
                return new PickupTilesCE();
            case "insert":
                return new InsertTilesCE();
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
            case "refresh":
                return new RefreshCE();
            case "colors":
                return new ColorsCE();
        }
        return new InvalidCE();
    }
}
