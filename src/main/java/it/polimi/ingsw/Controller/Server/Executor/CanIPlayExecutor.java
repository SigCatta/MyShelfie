package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class CanIPlayExecutor implements Executor {

    private GamesManager gamesManager;


    public CanIPlayExecutor() {
        this.gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {
        Game game = gamesManager.getGame(Integer.parseInt(data.get("GAMEID")));
        if(game == null) return;

        if(!game.getGameState().isCommandPossible(data.get("COMMAND")))return;

        ArrayList<Player> players = game.getPlayers();
        String nickname = data.get("NICKNAME");

        game.addPlayer(new Player(nickname));
    }
}
