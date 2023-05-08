package it.polimi.ingsw.network.client.InputStates.readers;

import it.polimi.ingsw.network.client.InputStates.readers.commands.*;

public class CommandExecutorFactory {
    public static CommandExecutor getCommand(String command) {
        switch (command) {
            case "pickup_tiles":
                return new PickupTilesCE();
            case "insert_tiles":
                return new InsertTilesCE();
            case "chat":
                return new ChatCE();
            case "disconnect":
                return new DisconnectCE();
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
