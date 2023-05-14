package it.polimi.ingsw.View.CLI.InputStates.reader;

import it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors.*;

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
            case "pchat":
                return new PChatCE();
            case "disconnect":
                return new DisconnectCE();
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
