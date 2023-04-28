package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Commands.CommandMapKey;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;


public class NewGameExecutor implements Executor { //TODO delete this class

    private Game game;

    public NewGameExecutor(Game game){
        this.game = game;
    }

    @Override
    public void execute(HashMap<String, String> data) {

        String nickname = data.get(String.valueOf(CommandMapKey.NICKNAME));

        game.addPlayer(new Player(nickname));

    }
}
