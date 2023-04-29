package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Server.ServerController.GamesManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

public class CanIPlayExecutor implements Executor {

    private Game game;

    public CanIPlayExecutor(Game game){
        this.game = game;
    }

    /**
     * connects the player to the chosen game
     */
    @Override
    public void execute(MessageToServer message) {
        if(!game.addPlayer(new Player(message.getNickname()))) return;
        GamesManager.getInstance().connectPlayer(message);
    }
}
