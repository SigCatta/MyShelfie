package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Server.ServerController.GamesManager;
import it.polimi.ingsw.model.Game;

import java.util.HashMap;

public class ByeExecutor implements Executor {

    private Game game;

    public ByeExecutor(Game game){
        this.game = game;
    }

    @Override
    public void execute(MessageToServer data) {

        String nickname = data.getNickname();

        game.disconnectPlayer(nickname);
        GamesManager.getInstance().removePlayer(data);
        //TODO disconnect the player from the gamesManager list
    }
}
