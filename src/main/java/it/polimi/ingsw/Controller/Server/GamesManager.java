package it.polimi.ingsw.Controller.Server;

import it.polimi.ingsw.Controller.Server.ServerMappable.ErrorMapper;
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
    private ThreeValuesHashMap<Integer, Game, List<SocketClientHandler>, CommandParser> gamesData;
    private Set<String> playersName = new HashSet<>();

    private static GamesManager gamesManagerInstance;

    private GamesManager(){
        gamesData = new ThreeValuesHashMap<>();
    }

    public static GamesManager getInstance(){
        if(gamesManagerInstance == null){
            gamesManagerInstance = new GamesManager();
        }
        return gamesManagerInstance;
    }

    /**
     * Adds a new game to the map
     * @return the game id of the created game
     */
    public int newGame(int numberOfPlayers){ //TODO make it private
        Game newGame = new Game(numberOfPlayers);

        int gameID = createID();
        newGame.setGameID(gameID);
        CommandParser parser = new CommandParser(newGame);

        gamesData.put(gameID, newGame, new ArrayList<>(), parser);

        newGame.setGameID(gameID);
        //TODO update the view so that it contains the gameID. note: it must be done from the model

        return gameID;
    }

    private synchronized void joinGame(int gameID, SocketClientHandler playerHandler){
        if(!gamesData.containsKey(gameID))return;
        gamesData.get2(gameID).add(playerHandler);
        Game game = gamesData.get1(gameID);
        game.addPlayer(new Player(playerHandler.getNickname()));
    }

    public synchronized boolean connectPlayer(HashMap<String, String> data, SocketClientHandler playerHandler) throws NumberFormatException{

        int gameID;
        String nickname = data.get("NICKNAME");
        if(nickname == null ) return false;

        if(playersName.contains(nickname)){
            playerHandler.sendCommand(ErrorMapper.getMap("The nickname already exists, choose a new one"));
            return false;
        }

        String command = data.get("COMMAND");

        if(command.equals("NEW_GAME")){
            int numberOfPlayers = Integer.parseInt(data.get("NUMBER_OF_PLAYERS"));
            gameID = newGame(numberOfPlayers);
        } else{
            gameID = Integer.parseInt(data.get("GAMEID"));
        }

        //the player also joins after creating a new game
        joinGame(gameID, playerHandler);

        playerHandler.setNickname(nickname);
        playerHandler.setGameID(gameID);

        return true;
    }

    public void onCommandReceived(HashMap<String, String> data){
        try{
            int gameID = Integer.parseInt(data.get("GAMEID"));
            gamesData.get3(gameID).parse(data);

        }catch (NumberFormatException ignore){}
    }

    /**
     * create a non-existing id
     */
    private int createID(){
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

    public void removePlayer(SocketClientHandler socketClientHandler){
        playersName.remove(socketClientHandler.getNickname());
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
