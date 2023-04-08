package it.polimi.ingsw.Controller.Executor.Server;

import java.util.HashMap;

public class JoinExecutor implements Executor{
    @Override
    public void execute(HashMap<String, String> data) {
        String nickname = data.get("nickname");
        String gameId = data.get("gameID");

        //Game gameRequested = gamesManager.getGame("gameID");
        //if(gameRequested == null) return;

        //if(!gameRequested.addPlayer(new Player("nickname"))){
        //      TODO could not connect to the server because the game is full
        // };
    }
}
