package it.polimi.ingsw.Controller.Executor.Server;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesManager;

import java.util.HashMap;

public class ByeExecutor implements Executor{

    private GamesManager gamesManager;

    public ByeExecutor(){
        GamesManager gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {
        String nickname = data.get("nickname");
        String gameId = data.get("gameID");

        String message = data.get("message");

        //TODO

        Game game = gamesManager.getGame(Integer.parseInt(gameId));

    }
}
