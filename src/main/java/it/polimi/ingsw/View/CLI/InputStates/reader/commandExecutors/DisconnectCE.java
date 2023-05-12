package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.Controller.Client.ByeMTS;
import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.CLI.InputStates.NicknameState;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class DisconnectCE implements CommandExecutor {

    /**
     * After asking for confirmation, removes the player from the current game
     */
    @Override
    public void execute() {
        System.out.println("Are you sure you want to leave? (y/n)");
        String input = getInput();
        if (input.equalsIgnoreCase("y")) {
            System.out.println("Leaving...");
            SocketClient.getInstance().sendCommand(new ByeMTS());
            InputStatePlayer.getInstance().setState(new NicknameState());
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
