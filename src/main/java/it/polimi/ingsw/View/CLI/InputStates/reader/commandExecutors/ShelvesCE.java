package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.View.CLI.Elements.Views.ShelfView;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;

import java.util.ArrayList;

public class ShelvesCE implements CommandExecutor {

    /**
     * Asks for which player's the user wants to see the shelf and prints it
     */
    @Override
    public void execute() {
        while (true) {

            System.out.println("Whose shelf do you want to see?");
            String input = Reader.getInput();
            if (input.equals(".")) return;
            if (!PlayersRepresentation.getInstance().getPlayersList().contains(input)) {
                System.out.println("The player " + input + " does not exist!");
                continue;
            }
            Printer.clearConsole();
            ArrayList<String> output = ShelfView.getInstance().getOtherShelvesPrint(input);
            output.forEach(System.out::println);
            Printer.addAvailableCommands(new ArrayList<>()).forEach(System.out::println);
            break;
        }
    }
}
