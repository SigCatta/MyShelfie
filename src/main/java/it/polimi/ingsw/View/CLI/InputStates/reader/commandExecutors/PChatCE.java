package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.network.client.SocketClient;

/**
 * Lets the player send a private chat message
 */
public class PChatCE implements CommandExecutor {
    /**
     * Asks for information about the private message
     */
    @Override
    public void execute() {
        System.out.println("Who do you want to send a private message to?");
        String receiver = Reader.getInput();
        System.out.println("Insert message: ");
        String input = Reader.getInput();
        if (input.equals(".")) return;
        SocketClient.getInstance().sendCommand(new ChatMTS(input, receiver));
    }
}
