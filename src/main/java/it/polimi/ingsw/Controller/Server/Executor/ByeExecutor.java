package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesManager;

import java.util.HashMap;

public class ByeExecutor implements Executor {

    private GamesManager gamesManager;

    public ByeExecutor(){
        gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {

        String gameId = data.get("GAMEID");

        Game game = gamesManager.getGame(Integer.parseInt(gameId));
        if(!game.getGameState().isCommandPossible(data.get("COMMAND")))return;

        String nickname = data.get("NICKNAME");

        game.disconnectPlayer(nickname);
    }
}
