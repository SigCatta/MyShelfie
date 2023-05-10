package it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class ChatCE implements CommandExecutor {

    /**
     * Asks for which message the player want's to send through the chat and sends it
     */
    @Override
    public void execute() {
        System.out.println("Insert message: ");
        String input = getInput();
        if (input.equals(".")) return;
        SocketClient.getInstance().sendCommand(new ChatMTS(input));
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
