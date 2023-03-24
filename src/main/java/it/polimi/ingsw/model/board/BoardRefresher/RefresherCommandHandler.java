package it.polimi.ingsw.model.board.BoardRefresher;

import java.util.ArrayList;
import java.util.List;


public class RefresherCommandHandler {
    private List<RefresherCommand> commands = new ArrayList<>();

    // Add command to the list
    public void addCommand(RefresherCommand command) {
        commands.add(command);
    }

    // Execute every method refillBoard of the instances in the list
    public void executeCommands() {
        for (RefresherCommand command : commands) {
            command.refillBoard();
        }
    }
}