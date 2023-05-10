package it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors;

public class InvalidCE implements CommandExecutor {

    /**
     * Tells the user the command given as input in invalid
     */
    @Override
    public void execute() {
        System.out.println("ERROR: invalid command!");
    }
}
