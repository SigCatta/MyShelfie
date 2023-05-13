package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.Printer;

public class RefreshCE implements CommandExecutor {

    /**
     * Clears the console and prints an updated home screen
     */
    @Override
    public void execute() {
        Printer.clearConsole();
        Printer.getInstance().update();
    }
}
