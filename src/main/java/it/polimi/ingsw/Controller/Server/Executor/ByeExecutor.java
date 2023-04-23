package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;

import java.util.HashMap;

public class ByeExecutor implements Executor {

    private Game game;

    public ByeExecutor(Game game){
        this.game = game;
    }

    @Override
    public void execute(HashMap<String, String> data) {

        String command = data.get("COMMAND");

        if(!game.getGameState().isCommandPossible(command))return;

        String nickname = data.get("NICKNAME");

        game.disconnectPlayer(nickname);
        //TODO disconnect the player from the gamesManager list
    }
}
