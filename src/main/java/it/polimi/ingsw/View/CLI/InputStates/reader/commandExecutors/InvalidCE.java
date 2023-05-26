package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;


/**
 * Tells the user an input in invalid
 */
public class InvalidCE implements CommandExecutor {

    /**
     * Tells the user the command given as input in invalid
     */
    @Override
    public void execute() {
        System.out.println("ERROR: invalid command!");
    }
}
