package it.polimi.ingsw.network.client.InputStates.readers.commandExecutors;

import it.polimi.ingsw.View.CLI.PersonalGoalView;

import java.util.ArrayList;

public class PersonalGoalCE implements CommandExecutor {

    /**
     * Prints the player's personal goal cad and a brief description on how to get points
     */
    @Override
    public void execute() {
        PersonalGoalView pgv = new PersonalGoalView();
        pgv.addDescription(pgv.getPrint(new ArrayList<>())).forEach(System.out::println);
    }
}
