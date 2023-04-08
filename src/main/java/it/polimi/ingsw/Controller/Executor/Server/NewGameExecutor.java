package it.polimi.ingsw.Controller.Executor.Server;

import java.util.HashMap;

public class NewGameExecutor implements Executor{
    @Override
    public void execute(HashMap<String, String> data) {

        String nickname = data.get("nickname");
        String message = data.get("message");

        //int gameID = gamesManager.addGame();
        //TODO send gameID back to the user

    }
}
