package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.Enum.ErrorCode;
import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;
import it.polimi.ingsw.View.VirtualView.ModelObservers.PlayerView;
import it.polimi.ingsw.View.VirtualView.ModelObservers.ShelfView;
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
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("Insert a valid game id", ErrorCode.NOID));
            System.out.println("Insert a valid game id"); //TODO remove
            return;
        }
        if(game.getPlayers().size() == game.getMAX_PLAYER_NUMBER()) {
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("The chosen game is already full", ErrorCode.GAMEFULL));
            System.out.println("The chosen game is already full");//TODO remove
            return;
        }

        playerHandler.setGameID(gameID);

        game.getVirtualView().addClient(playerHandler);

        Player newPlayer = new Player(message.getNickname());
        new PlayerView(newPlayer, game.getVirtualView());// links the player observer to the player
        new ShelfView(newPlayer, game.getVirtualView()); // links the shelf observer to the shelf
        game.addPlayer(newPlayer);

        playerHandler.setGameID(gameID);    //the gameid is also definitive

        game.getVirtualView().updateAllPlayers();
        game.notifyObservers();
    }
}
