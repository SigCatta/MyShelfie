package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.CanIPlayMTS;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.VirtualView.ModelObservers.PlayerVV;
import it.polimi.ingsw.VirtualView.ModelObservers.ShelfVV;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.network.server.SocketClientHandler;

public class CanIPlayExecutor implements Executor {
    /**
     * connects a player to an existing game
     */
    public static void execute(CanIPlayMTS message) throws NumberFormatException {

        SocketClientHandler playerHandler = message.getSocketClientHandler();

        int gameID = message.getNewGameID();

        Game game = GamesManager.getInstance().getGame(gameID);

        if (game == null) {
            message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.NOID, true));
            System.out.println("Insert a valid game id"); //TODO remove
            return;
        }
        if (game.getPlayers().size() == game.getMAX_PLAYER_NUMBER()) {
            message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.GAMEFULL, true));
            System.out.println("The chosen game is already full");//TODO remove
            return;
        }

        playerHandler.setGameID(gameID);

        game.getVirtualView().addClient(playerHandler);

        Player newPlayer = new Player(message.getNickname());
        new PlayerVV(newPlayer, game.getVirtualView());// links the player observer to the player
        new ShelfVV(newPlayer, game.getVirtualView()); // links the shelf observer to the shelf
        game.addPlayer(newPlayer);

        playerHandler.setGameID(gameID);    //the gameid is also definitive

        game.getVirtualView().updateAllPlayers();
        game.getVirtualView().updateAllShelvs();
        game.notifyObservers();
        message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.JOINED, false));
    }
}
