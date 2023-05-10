package it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.ShelfView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class ShelvesCE implements CommandExecutor {

    /**
     * Asks for which player's the user wants to see the shelf and prints it
     */
    @Override
    public void execute() {
        System.out.println("Whose shelf do you want to see?");
        String input = getInput();
        if (input.equals(".")) return;
        ArrayList<String> output = new ShelfView().getOtherShelvsPrint(input);
        output.forEach(System.out::println);
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
