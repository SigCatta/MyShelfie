package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Commands.CommandMapKey;
import it.polimi.ingsw.model.Game;

import java.util.HashMap;

public class DisconnectPlayerExecutor implements Executor{

    private Game game;

    public DisconnectPlayerExecutor(Game game){
        this.game = game;
    }

    @Override
    public void execute(HashMap<String, String> data) {
        String nickname = data.get(String.valueOf(CommandMapKey.NICKNAME));
        game.disconnectPlayer(nickname);
    }
}
