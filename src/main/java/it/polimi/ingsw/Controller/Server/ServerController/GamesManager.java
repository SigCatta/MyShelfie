package it.polimi.ingsw.Controller.Server.ServerController;

import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.*;

/**
 * Singleton class that contains the games that are being played
 */
public class GamesManager {

    /**
     * map of gameID / game / handler of the players / parser assigned for the game.
     * it contains the necessary data to manage the requests
     */
    private TwoValuesHashMap<Integer, Game, List<SocketClientHandler>> gamesData;
    /**
     * Important note: synchronize manually it in case of a set iteration
     */
    private final Set<String> PLAYERS_NAME = Collections.synchronizedSet(new HashSet<>());

    private static GamesManager gamesManagerInstance;

    private GamesManager(){
        gamesData = new TwoValuesHashMap<>();
    }

    public static GamesManager getInstance(){
        if(gamesManagerInstance == null){
            gamesManagerInstance = new GamesManager();
        }
        return gamesManagerInstance;
    }

    /**
     * Adds a new game to the map and creates a virtual view associated to that game.
     */
    public void newGame(MessageToServer message){

        if(PLAYERS_NAME.contains(message.getNickname())){
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("choose another nickname"));
        }

        NewGameMessage newGameMessage = (NewGameMessage) message;

        Game newGame = new Game(newGameMessage.getNumberOfPlayers());

        int gameID = createID();
        newGame.setGameID(gameID);
        gamesData.put(gameID, newGame, new ArrayList<>());

        VirtualView virtualView = new VirtualView(newGame); //creates a virtualView and assign it to the game
        newGame.setVirtualView(virtualView);

        virtualView.addClient(message.getSocketClientHandler());

        newGame.notifyObservers(); //shows the gameID to the creator of the game
    }

    /**
     * connects a player to an existing game
     */
    public void connectPlayer(MessageToServer message) throws NumberFormatException{

        if(PLAYERS_NAME.contains(message.getNickname())){
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("choose another nickname"));
        }

        CanIPlayMessage canIPlayMessage = (CanIPlayMessage) message;
        SocketClientHandler playerHandler = message.getSocketClientHandler();

        String nickname = canIPlayMessage.getNickname();
        int gameID = canIPlayMessage.getGameID();

        playerHandler.setNickname(nickname); //the nickname is definitive
        playerHandler.setGameID(gameID);    //the gameid is also definitive
    }

    public void onCommandReceived(MessageToServer message){
        ServerController.getInstance().visit(message, gamesData.get1(message.getGameID()));
    }

    /**
     * create a non-existing id
     */
    private synchronized int createID(){
        int MAX_VALUE = Integer.MAX_VALUE;

        int gameID = (int)(Math.random()*MAX_VALUE);

        while(gamesData.containsKey(gameID)){
            if(gameID == MAX_VALUE) gameID = 0;
            else gameID++;
        }
        return gameID;
    }

    public void endGame(int gameID){
        gamesData.remove(gameID);
    }

    public void removePlayer(MessageToServer message){
        SocketClientHandler socketClientHandler = message.getSocketClientHandler();
        PLAYERS_NAME.remove(socketClientHandler.getNickname());
        gamesData.get2(socketClientHandler.getGameID()).remove(socketClientHandler);
    }

    public void removePlayer(SocketClientHandler socketClientHandler){
        PLAYERS_NAME.remove(socketClientHandler.getNickname());
        gamesData.get2(socketClientHandler.getGameID()).remove(socketClientHandler);
    }

    public Game getGame(int gameID){
        return gamesData.get1(gameID);
    }

    public int getNumberOfGames(){
        return gamesData.size();
    }

    public void onConnectionLost(SocketClientHandler socketClientHandler) {
        //TODO update the model so every player knows about the disconnection (new executor)
        //TODO in the executor check if the game has only 1 player left, in that case declare the win
    }

    public void onConnectionRestored(SocketClientHandler socketClientHandler){
        //TODO update the model so every player knows about the reconnection (new executor)
    }
}
