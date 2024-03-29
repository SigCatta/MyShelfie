package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.View.CLI.Elements.Views.PersonalGoalView;

import java.util.ArrayList;

/**
 * Prints the player's personal goal card and a brief description
 */
public class PersonalGoalCE implements CommandExecutor {

    /**
     * Prints the player's personal goal cad and a brief description on how to get points
     */
    @Override
    public void execute() {
        PersonalGoalView pgv = PersonalGoalView.getInstance();

        Printer.clearConsole();
        pgv.addDescription(pgv.getPrint(new ArrayList<>())).forEach(System.out::println);
        Printer.addAvailableCommands(new ArrayList<>()).forEach(System.out::println);
    }
}
