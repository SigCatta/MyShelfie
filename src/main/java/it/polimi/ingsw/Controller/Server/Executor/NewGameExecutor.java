package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.NewGameMTS;
import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.VirtualView.ModelObservers.PlayerVV;
import it.polimi.ingsw.VirtualView.ModelObservers.ShelfVV;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

public class NewGameExecutor implements Executor {
    /**
     * Adds a new game to the map and creates a virtual view associated to that game.
     */
    public static void execute(NewGameMTS message) {

        Game newGame = new Game(message.getNumberOfPlayers());

        int gameID = GamesManager.getInstance().createID();
        newGame.setGameID(gameID);

        //by doing this, the handler will contain the gameid and nickname for the whole game (the client will not send it anymore)
        message.getSocketClientHandler().setGameID(gameID);

        GamesManager.getInstance().putGame(gameID, newGame);

        VirtualView virtualView = new VirtualView(newGame); //creates a virtualView and assign it to the game
        newGame.setVirtualView(virtualView);

        virtualView.addClient(message.getSocketClientHandler());

        Player newPlayer = new Player(message.getNickname());
        new PlayerVV(newPlayer, virtualView);// links the player observer to the player
        new ShelfVV(newPlayer, virtualView); // links the shelf observer to the shelf
        newGame.addPlayer(newPlayer);

        message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.CREATED, false));

        newGame.notifyObservers(); //shows the gameID to the creator of the game
    }
}
