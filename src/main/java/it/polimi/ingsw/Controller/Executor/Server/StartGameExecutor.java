package it.polimi.ingsw.Controller.Executor.Server;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesManager;

import java.util.HashMap;

public class StartGameExecutor implements Executor {
    private GamesManager gamesManager;

    public StartGameExecutor() {
        this.gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {
        String nickname = data.get("nickname");
        String gameID = data.get("GAME_ID");

        Game game = gamesManager.getGame(Integer.parseInt(gameID));

        if(!game.getGameOwner().equals(nickname)) return;

        game.start();
    }
}
