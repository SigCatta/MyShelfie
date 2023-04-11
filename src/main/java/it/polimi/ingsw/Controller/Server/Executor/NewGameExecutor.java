package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.GamesManager;

import java.util.HashMap;


public class NewGameExecutor implements Executor {

    private GamesManager gamesManager;

    public NewGameExecutor(){
        gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {

        String nickname = data.get("nickname");
        String message = data.get("message");

        int gameID = gamesManager.addGame();

        //TODO send gameID back to the user

    }
}
