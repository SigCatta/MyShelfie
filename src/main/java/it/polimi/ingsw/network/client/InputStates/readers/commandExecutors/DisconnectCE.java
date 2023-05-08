package it.polimi.ingsw.network.client.InputStates.readers.commandExecutors;

import it.polimi.ingsw.Controller.Client.ByeMTS;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class DisconnectCE implements CommandExecutor {
    @Override
    public void execute() {
        System.out.println("Are you sure you want to leave? (y/n)");
        String input = getInput();
        if (input.equalsIgnoreCase("y")) {
            System.out.println("Leaving...");
            SocketClient.getInstance().sendCommand(new ByeMTS());
            // TODO where should the user be sent?
        }
    }

    private String getInput() {
        try {
            return readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
