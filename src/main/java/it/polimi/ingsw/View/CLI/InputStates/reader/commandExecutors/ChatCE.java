package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.network.client.SocketClient;

/**
 * Lets the player send a public chat message
 */
public class ChatCE implements CommandExecutor {

    /**
     * Asks for which message the player want's to send through the chat and sends it
     */
    @Override
    public void execute() {
        System.out.println("Insert message: ");
        String input = Reader.getInput();
        if (input.equals(".")) return;
        SocketClient.getInstance().sendCommand(new ChatMTS(input, null));
    }
}
