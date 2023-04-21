package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.Controller.Server.GamesManager;

import java.util.HashMap;


public class NewGameExecutor implements Executor {

    private GamesManager gamesManager;

    public NewGameExecutor(){
        gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {

        Game game = gamesManager.getGame(Integer.parseInt(data.get("GAMEID")));
        if(!game.getGameState().isCommandPossible(data.get("COMMAND")))return;

        String nickname = data.get("NICKNAME");
        int maxPlayers = Integer.parseInt(data.get("MAX_PLAYERS"));

        int gameID = gamesManager.addGame(maxPlayers);
    }
}
