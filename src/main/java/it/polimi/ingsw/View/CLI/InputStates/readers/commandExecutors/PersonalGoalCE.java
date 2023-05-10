package it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.PersonalGoalView;
import it.polimi.ingsw.View.CLI.Elements.Printer;

import java.util.ArrayList;

public class PersonalGoalCE implements CommandExecutor {

    /**
     * Prints the player's personal goal cad and a brief description on how to get points
     */
    @Override
    public void execute() {
        Printer.clearConsole();
        PersonalGoalView pgv = new PersonalGoalView();
        pgv.addDescription(pgv.getPrint(new ArrayList<>())).forEach(System.out::println);
        Printer.addAvailableCommands(new ArrayList<>()).forEach(System.out::println);
    }
}
