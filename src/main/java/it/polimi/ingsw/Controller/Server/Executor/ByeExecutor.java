package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.MessageToServer;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.model.Game;

public class ByeExecutor implements Executor {

    public static void execute(MessageToServer message) {

        Game game = message.getGame();

        //end the message if one player disconnects
        game.end();
        //disconnect the player from the gamesManager list
        GamesManager.getInstance().endGame(message.getGameID());
    }
}
