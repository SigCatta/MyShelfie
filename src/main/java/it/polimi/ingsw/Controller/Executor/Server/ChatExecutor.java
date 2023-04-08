package it.polimi.ingsw.Controller.Executor.Server;

import java.util.HashMap;

public class ChatExecutor implements Executor {

    @Override
    public void execute(HashMap<String, String> data) {
        String nickname = data.get("nickname");
        String gameId = data.get("gameID");
        String command = data.get("command");

        String message = data.get("message");

        //TODO call the method to push the message in the chat

    }
}
