package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Server.ServerController.GamesManager;
import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.network.server.SocketClientHandler;

public class CanIPlayExecutor implements Executor {
    /**
     * connects a player to an existing game
     */
    public static void execute(CanIPlayMessage message) throws NumberFormatException{

        SocketClientHandler playerHandler = message.getSocketClientHandler();

        int gameID = message.getNewGameID();

        Game game = GamesManager.getInstance().getGame(gameID);

        if(game == null) {
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("Insert a valid game id"));
            System.out.println("Insert a valid game id"); //TODO remove
            return;
        }
        if(game.getPlayers().size() == game.getMAX_PLAYER_NUMBER()) {
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("The game chosen is already full"));
            System.out.println("The game chosen is already full");//TODO remove
            return;
        }

        playerHandler.setGameID(gameID);    //the gameid is also definitive

        game.addPlayer(new Player(message.getNickname()));
    }
}
