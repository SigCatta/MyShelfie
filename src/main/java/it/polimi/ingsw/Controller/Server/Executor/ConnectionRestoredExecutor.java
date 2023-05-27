package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;

/**
 * This class class is responsible for executing the restoration of a player's connection in the game.
 */
public class ConnectionRestoredExecutor {
    public static void execute(Game game, String nickname) {
        if (nickname == null) return;
        game.getPlayer(nickname).setConnected(true);
    }
}
