package it.polimi.ingsw.network.client.InputStates.readers.commands;

import it.polimi.ingsw.View.CLI.ShelfView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class ShelvesCE implements CommandExecutor {
    @Override
    public void execute() {
        System.out.println("Whose shelf do you want to see?");
        String input = getInput();
        if (input.equals(".")) return;
        ArrayList<String> output = new ShelfView().getOtherShelvsPrint(input);
        output.forEach(System.out::println);
    }

    private String getInput() {
        try {
            return readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
