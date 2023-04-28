package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Commands.CommandMapKey;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class CanIPlayExecutor implements Executor {

    private Game game;

    public CanIPlayExecutor(Game game){
        this.game = game;
    }

    @Override
    public void execute(HashMap<String, String> data) {
        String command = data.get(String.valueOf(CommandMapKey.COMMAND));

        if(!game.getGameState().isCommandPossible(command))return;

        ArrayList<Player> players = game.getPlayers();
        String nickname = data.get(String.valueOf(CommandMapKey.NICKNAME));

        game.addPlayer(new Player(nickname));
    }
}
