package it.polimi.ingsw.network.client.InputStates.readers.commandExecutors;

import it.polimi.ingsw.View.CLI.CommonGoalView;

import java.util.ArrayList;

public class CommonGoalCE implements CommandExecutor {

    /**
     * Prints the common goals with a brief desciption next to them
     */
    @Override
    public void execute() {
        CommonGoalView cgv = new CommonGoalView();
        cgv.addDescription(cgv.getPrint(new ArrayList<>())).forEach(System.out::println);
    }
}
