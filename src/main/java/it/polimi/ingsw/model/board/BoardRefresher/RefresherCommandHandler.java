package it.polimi.ingsw.model.board.BoardRefresher;

import java.util.ArrayList;
import java.util.List;


public class RefresherCommandHandler {
    private final List<RefresherCommand> COMMANDS = new ArrayList<>();

    // Add command to the list
    public void addCommand(RefresherCommand command) {
        COMMANDS.add(command);
    }

    // Execute every method refillBoard of the instances in the list
    public void executeCommands() {
        for (RefresherCommand command : COMMANDS) {
            command.refillBoard();
        }
    }
}