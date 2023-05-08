package it.polimi.ingsw.network.client.InputStates.readers.commands;

public class InvalidCE implements CommandExecutor {
    @Override
    public void execute() {
        System.out.println("ERROR: invalid command!");
    }
}
