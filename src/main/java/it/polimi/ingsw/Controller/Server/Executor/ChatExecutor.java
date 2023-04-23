package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;

import java.util.HashMap;

public class ChatExecutor implements Executor {

    private Game game;

    public ChatExecutor(Game game){
        this.game = game;
    }


    @Override
    public void execute(HashMap<String, String> data) {

        String command = data.get("COMMAND");

        if(!game.getGameState().isCommandPossible(command))return;

        String nickname = data.get("NICKNAME");
        String message = data.get("MESSAGE");


        //TODO call the method to push the message in the chat

    }
}
