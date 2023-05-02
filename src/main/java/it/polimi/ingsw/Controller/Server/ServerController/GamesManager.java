package it.polimi.ingsw.Controller.Server.ServerController;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Server.Executor.ConnectionRestoredExecutor;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.PregameState;
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
    private HashMap<Integer, Game> gamesData;
    /**
     * Important note: synchronize manually it in case of a set iteration
     */
    private final Set<String> PLAYERS_NAME = Collections.synchronizedSet(new HashSet<>());

    private static GamesManager gamesManagerInstance;

    private GamesManager(){
        gamesData = new HashMap<>();
    }

    public static GamesManager getInstance(){
        if(gamesManagerInstance == null){
            gamesManagerInstance = new GamesManager();
        }
        return gamesManagerInstance;
    }

    /**
     * adds a game to the hashmap
     * @param key the gameid
     * @param value the game
     */
    public void putGame(int key, Game value){
        gamesData.put(key, value);
    }

    public Game getGame(int key){
        return gamesData.get(key);
    }

    public boolean addNickname(String nickname){
        return PLAYERS_NAME.add(nickname);
    }

    public void onCommandReceived(MessageToServer message){
        message.setGame(gamesData.get(message.getGameID())); //adds to the header of the message the game of the player
        ServerController.getInstance().visit(message);
    }

    /**
     * create a non-existing id
     */
    public synchronized int createID(){
        int MAX_VALUE = Integer.MAX_VALUE;

        int gameID = (int)(Math.random()*MAX_VALUE);
        if(gameID == 0) gameID ++; //the gameID cannot be 0 because it represents the not connection to any game

        while(gamesData.containsKey(gameID)){
            if(gameID == MAX_VALUE) gameID = 1;
            else gameID++;
        }
        System.out.println("It's a success fully!! New game created with gameID: " + gameID); //TODO remove
        return gameID;
    }

    public void removePlayer(SocketClientHandler socketClientHandler){
        PLAYERS_NAME.remove(socketClientHandler.getNickname());
        Game game = gamesData.get(socketClientHandler.getGameID());
        if(game != null){
            gamesData.get(socketClientHandler.getGameID()).disconnectPlayer(socketClientHandler.getNickname());
        }
    }

    public void endGame(int gameID){
        gamesData.remove(gameID);
    }

    public int getNumberOfGames(){
        return gamesData.size();
    }

    public void onConnectionLost(SocketClientHandler socketClientHandler) {
        //update the model so every player knows about the disconnection
        //TODO in the executor check if the game has only 1 player left, in that case declare the win
        if(socketClientHandler.getGameID() == 0) { //this means it has not been assigned to any game
            socketClientHandler.disconnect();
        } else if(gamesData.get(socketClientHandler.getGameID()).getGameState() instanceof PregameState){
            socketClientHandler.disconnect();
        }
    }

    public void onConnectionRestored(SocketClientHandler socketClientHandler){
        //update the model so every player knows about the reconnection
        ConnectionRestoredExecutor.execute(gamesData.get(socketClientHandler.getGameID()), socketClientHandler.getNickname());
    }
}
