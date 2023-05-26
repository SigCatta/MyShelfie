package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.View.CLI.Elements.Views.CommonGoalView;

import java.util.ArrayList;

/**
 * Prints the game's common goal cards with a description on what to do to complete them
 */
public class CommonGoalCE implements CommandExecutor {

    /**
     * Prints the common goals with a brief description next to them
     */
    @Override
    public void execute() {
        CommonGoalView cgv = CommonGoalView.getInstance();

        Printer.clearConsole();
        cgv.addDescription(cgv.getPrint(new ArrayList<>())).forEach(System.out::println);
        Printer.addAvailableCommands(new ArrayList<>()).forEach(System.out::println);
    }
}
