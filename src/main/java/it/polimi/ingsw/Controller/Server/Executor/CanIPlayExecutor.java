package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Server.ServerController.GamesManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

public class CanIPlayExecutor implements Executor {

    /**
     * connects the player to the chosen game
     */
    public static void execute(MessageToServer message) {
        Game game = message.getGame();
        if(!game.addPlayer(new Player(message.getNickname()))) return;
        GamesManager.getInstance().joinPlayer(message);
    }
}
