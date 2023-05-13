package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;

public class ConnectionRestoredExecutor {
    public static void execute(Game game, String nickname) {
        if(nickname == null) return; //TODO modify so that it doesn't do anything if the player is not in a game
        game.getPlayer(nickname).setConnected(true);
    }
}
