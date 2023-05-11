package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.Printer;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class ColorsCE implements CommandExecutor {

    /**
     * Asks the user to choose between using colors or letters for item tiles
     */
    @Override
    public void execute() {
        System.out.println("Do you want to use colors? (y/n)");
        Printer.enableCLIColors(getInput().equals("y"));
        Printer.clearConsole();
        Printer.printHomeScreen();
    }

    /**
     * Reads user input
     *
     * @return user input
     */
    private String getInput() {
        try {
            return readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
