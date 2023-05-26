package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;

/**
 * Prints and updated game home screen
 */
public class RefreshCE implements CommandExecutor {

    /**
     * Clears the console and prints an updated home screen
     */
    @Override
    public void execute() {
        Reader.getInstance().setReading(false);
        Printer.clearConsole();
        Printer.getInstance().update();
    }
}
