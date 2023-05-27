package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.CanIPlayMTS;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.VirtualView.ModelObservers.PlayerVV;
import it.polimi.ingsw.VirtualView.ModelObservers.ShelfVV;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.network.server.SocketClientHandler;

/**
 * This class is responsible for executing the {@link CanIPlayMTS} command received from the client.
 * It connects a player to an existing game if the game is in the pregame state and has available slots.
 */
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
            return;
        }
        if (game.getPlayers().size() == game.getMAX_PLAYER_NUMBER()) {
            message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.GAMEFULL, true));
            return;
        }
        if (game.getGameState() != GameState.PREGAME) {
            message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.GAMEFULL, true));
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
        game.getVirtualView().updateAllShelves();
        game.getVirtualView().updateAllCommonGoals();
        game.notifyObservers();
        game.getVirtualView().send(new EchoMTC(EchoID.JOINED, false));
    }
}
