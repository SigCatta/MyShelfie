package it.polimi.ingsw.network.client.InputStates.readers.commands;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.network.client.InputStates.readers.commands.CommandExecutor;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class ChatCE implements CommandExecutor {
    @Override
    public void execute() {
        System.out.println("Insert message: ");
        String input = getInput();
        if (input.equals(".")) return;
        SocketClient.getInstance().sendCommand(new ChatMTS(input));
    }

    private String getInput() {
        try {
            return readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
