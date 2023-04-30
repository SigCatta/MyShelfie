package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.Controller.Server.ServerController.GamesManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;


public class NewGameExecutor implements Executor { //TODO delete this class

    public static void execute(MessageToServer message) {
        GamesManager.getInstance().newGame((NewGameMessage) message);
        message.getSocketClientHandler().setGameID(message.getGameID());
        message.getSocketClientHandler().setNickname(message.getNickname());
    }
}
