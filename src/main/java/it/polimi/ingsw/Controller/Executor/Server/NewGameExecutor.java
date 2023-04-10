package it.polimi.ingsw.Controller.Executor.Server;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesManager;

import java.util.HashMap;

public class NewGameExecutor implements Executor{

    private GamesManager gamesManager = GamesManager.getInstance();

    public NewGameExecutor(){
        GamesManager gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {

        String nickname = data.get("nickname");
        String message = data.get("message");

        int gameID = gamesManager.addGame();

        //TODO send gameID back to the user

    }
}
