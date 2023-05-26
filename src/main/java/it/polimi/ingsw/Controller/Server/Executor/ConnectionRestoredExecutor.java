package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;

public class ConnectionRestoredExecutor {
    public static void execute(Game game, String nickname) {
        if (nickname == null) return;
        game.getPlayer(nickname).setConnected(true);
    }
}
