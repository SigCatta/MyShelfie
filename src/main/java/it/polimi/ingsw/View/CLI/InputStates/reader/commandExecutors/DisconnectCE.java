package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.Controller.Client.ByeMTS;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.network.client.SocketClient;

/**
 * Disconnects the player from the game (this will result in the program terminating)
 */
public class DisconnectCE implements CommandExecutor {

    /**
     * After asking for confirmation, removes the player from the current game
     */
    @Override
    public void execute() {
        System.out.println("Are you sure you want to leave? (y/n)");
        String input = Reader.getInput();
        if (input.equalsIgnoreCase("y")) {
            System.out.println("Leaving...");
            SocketClient.getInstance().sendCommand(new ByeMTS());
        }
    }
}
