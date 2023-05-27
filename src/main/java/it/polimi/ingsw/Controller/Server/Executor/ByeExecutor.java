package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.MessageToServer;
import it.polimi.ingsw.model.Game;

/**
 * This class is responsible for executing the {@link it.polimi.ingsw.Controller.Client.ByeMTS} command received from the client.
 * It ends the game if one player disconnects.
 */

public class ByeExecutor implements Executor {

    public static void execute(MessageToServer message) {

        Game game = message.getGame();

        //end the game if one player disconnects
        if (game == null) return;

        game.end();
        game.disconnectPlayer(message.getNickname());
    }
}
