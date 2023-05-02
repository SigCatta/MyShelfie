package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.Controller.Server.ServerController.GamesManager;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

public class NewGameExecutor implements Executor{
    /**
     * Adds a new game to the map and creates a virtual view associated to that game.
     */
    public static void execute(NewGameMessage message){

        Game newGame = new Game(message.getNumberOfPlayers());

        int gameID = GamesManager.getInstance().createID();
        newGame.setGameID(gameID);

        //by doing this, the handler will contain the gameid and nickname for the whole game (the client will not send it anymore)
        message.getSocketClientHandler().setGameID(gameID);

        GamesManager.getInstance().putGame(gameID, newGame);

        VirtualView virtualView = new VirtualView(newGame); //creates a virtualView and assign it to the game
        newGame.setVirtualView(virtualView);

        virtualView.addClient(message.getSocketClientHandler());

        newGame.addPlayer(new Player(message.getNickname()));

        newGame.notifyObservers(); //shows the gameID to the creator of the game
    }
}
