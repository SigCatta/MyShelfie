package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;

public class ConnectionFailedExecutor implements Executor{

    public static void execute(Game game, String nickname) {
        if(game.getPlayer(nickname) == null) return;
        game.disconnectPlayer(nickname);
    }

}
