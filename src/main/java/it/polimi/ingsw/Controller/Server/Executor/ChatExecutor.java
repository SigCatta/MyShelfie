package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesManager;

import java.util.HashMap;

public class ChatExecutor implements Executor {

    private GamesManager gamesManager;

    public ChatExecutor(){
        gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {
        String nickname = data.get("NICKNAME");
        String gameId = data.get("GAMEID");

        String message = data.get("MESSAGE");

        Game game = gamesManager.getGame(Integer.parseInt(gameId));

        //TODO call the method to push the message in the chat

    }
}
