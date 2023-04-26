package it.polimi.ingsw.Controller.Server;

import it.polimi.ingsw.Controller.Server.VirtualView.Messages.ErrorMessage;
import it.polimi.ingsw.Controller.Server.VirtualView.VirtualView;
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
    /**
     * Important note: synchronize manually it in case of a set iteration
     */
    private final Set<String> PLAYERS_NAME = Collections.synchronizedSet(new HashSet<>());

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
     * Adds a new game to the map and creates a virtual view associated to that game.
     * @return the game id of the created game used to make the creator join the game automatically
     */
    public int newGame(int numberOfPlayers){ //TODO make it private
        Game newGame = new Game(numberOfPlayers);

        int gameID = createID();
        newGame.setGameID(gameID);
        CommandParser parser = new CommandParser(newGame);

        gamesData.put(gameID, newGame, new ArrayList<>(), parser);

        newGame.setGameID(gameID);

        VirtualView virtualView = new VirtualView(newGame); //creates a virtualView and assign it to the game
        newGame.setVirtualView(virtualView);

        newGame.notifyObservers(); //shows the gameID to the creator of the game

        return gameID;
    }

    private synchronized void joinGame(int gameID, SocketClientHandler playerHandler){
        if(!gamesData.containsKey(gameID))return;
        gamesData.get2(gameID).add(playerHandler);
        Game game = gamesData.get1(gameID);

        if(!game.addPlayer(new Player(playerHandler.getNickname()))){
            playerHandler.sendCommand(new ErrorMessage("the game is full"));
            playerHandler.disconnect();
            return;
        }

        game.getVirtualView().addClient(playerHandler);
    }

    public boolean connectPlayer(HashMap<String, String> data, SocketClientHandler playerHandler) throws NumberFormatException{

        int gameID;
        String nickname = data.get("NICKNAME");
        if(nickname == null ) return false;

        if(PLAYERS_NAME.contains(nickname)){
            playerHandler.sendCommand(new ErrorMessage("The nickname already exists, choose a new one"));
            return false;
        }

        String command = data.get("COMMAND");

        if(command.equals("NEW_GAME")){
            int numberOfPlayers = Integer.parseInt(data.get("NUMBER_OF_PLAYERS"));
            gameID = newGame(numberOfPlayers); //creates a new game with the virtual view
        } else{
            gameID = Integer.parseInt(data.get("GAMEID"));
        }

        playerHandler.setNickname(nickname);
        playerHandler.setGameID(gameID);

        //the player also joins after creating a new game
        joinGame(gameID, playerHandler);

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
