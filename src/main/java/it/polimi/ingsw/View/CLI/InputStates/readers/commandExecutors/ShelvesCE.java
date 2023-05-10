package it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.View.CLI.Elements.ShelfView;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class ShelvesCE implements CommandExecutor {

    /**
     * Asks for which player's the user wants to see the shelf and prints it
     */
    @Override
    public void execute() {
        while (true){

        System.out.println("Whose shelf do you want to see?");
        String input = getInput();
        if (input.equals(".")) return;
        if(!PlayersRepresentation.getInstance().getPlayersList().contains(input)){
            System.out.println("The player " + input + " does not exist!");
            continue;
        }
        Printer.clearConsole();
        ArrayList<String> output = new ShelfView().getOtherShelvsPrint(input);
        output.forEach(System.out::println);
        Printer.addAvailableCommands(new ArrayList<>()).forEach(System.out::println);
        break;
        }
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
