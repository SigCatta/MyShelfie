package it.polimi.ingsw.Controller.Server.ServerController;

import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.Controller.Server.Executor.ConnectionFailedExecutor;
import it.polimi.ingsw.Controller.Server.Executor.ConnectionRestoredExecutor;
import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.PregameState;
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
     * Adds a new game to the map and creates a virtual view associated to that game.
     */
    public synchronized void newGame(MessageToServer message){

        if(PLAYERS_NAME.contains(message.getNickname())){
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("choose another nickname"));
        }

        NewGameMessage newGameMessage = (NewGameMessage) message;

        Game newGame = new Game(newGameMessage.getNumberOfPlayers());

        int gameID = createID();
        newGame.setGameID(gameID);

        //by doing this, the handler will contain the gameid and nickname for the whole game (the client will not send it anymore)
        newGameMessage.getSocketClientHandler().setGameID(gameID);
        newGameMessage.getSocketClientHandler().setNickname(message.getNickname());

        gamesData.put(gameID, newGame);

        VirtualView virtualView = new VirtualView(newGame); //creates a virtualView and assign it to the game
        newGame.setVirtualView(virtualView);

        virtualView.addClient(message.getSocketClientHandler());

        newGame.addPlayer(new Player(message.getNickname()));

        newGame.notifyObservers(); //shows the gameID to the creator of the game
    }

    /**
     * connects a player to an existing game
     */
    public synchronized void joinPlayer(CanIPlayMessage message) throws NumberFormatException{

        if(PLAYERS_NAME.contains(message.getNickname())){
            System.out.println("Choose another nickname ");//TODO remove
            message.getSocketClientHandler().sendCommand(new ErrorMessageToClient("choose another nickname"));
            return;
        }

        SocketClientHandler playerHandler = message.getSocketClientHandler();

        String nickname = message.getNewNickname();
        int gameID = message.getNewGameID();

        Game game = gamesData.get(gameID);

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

        playerHandler.setNickname(nickname); //the nickname is definitive
        playerHandler.setGameID(gameID);    //the gameid is also definitive

        game.addPlayer(new Player(nickname));
    }

    public void onCommandReceived(MessageToServer message){
        message.setGame(gamesData.get(message.getGameID())); //adds to the header of the message the game of the player
        if(message instanceof CanIPlayMessage){
            message.setGame(gamesData.get(message.getGameID())); //TODO remove
        }
        ServerController.getInstance().visit(message);
    }

    /**
     * create a non-existing id
     */
    private synchronized int createID(){
        int MAX_VALUE = Integer.MAX_VALUE;

        int gameID = (int)(Math.random()*MAX_VALUE);
        if(gameID == 0) gameID ++; //the gameID cannot be 0 because it represents the not connection to any game

        while(gamesData.containsKey(gameID)){
            if(gameID == MAX_VALUE) gameID = 1;
            else gameID++;
        }
        System.out.println("your game id is " + gameID); //TODO remove
        return gameID;
    }

    public void endGame(int gameID){
        gamesData.remove(gameID);
    }

    public void removePlayer(SocketClientHandler socketClientHandler){
        PLAYERS_NAME.remove(socketClientHandler.getNickname());
        Game game = gamesData.get(socketClientHandler.getGameID());
        if(game != null){
            gamesData.get(socketClientHandler.getGameID()).disconnectPlayer(socketClientHandler.getNickname());
        }
    }

    public void removeGame(int gameID){
        gamesData.remove(gameID);
    }

    public Game getGame(int gameID){
        return gamesData.get(gameID);
    }

    public int getNumberOfGames(){
        return gamesData.size();
    }

    public void onConnectionLost(SocketClientHandler socketClientHandler) {
        //update the model so every player knows about the disconnection
        //TODO in the executor check if the game has only 1 player left, in that case declare the win
        if(socketClientHandler.getGameID() == 0) { //this means it has not been assigned to any game
            socketClientHandler.disconnect();
            return;
        } else if(gamesData.get(socketClientHandler.getGameID()).getGameState() instanceof PregameState){
            socketClientHandler.disconnect();
            return;
        }
        ConnectionFailedExecutor.execute(gamesData.get(socketClientHandler.getGameID()), socketClientHandler.getNickname());
    }

    public void onConnectionRestored(SocketClientHandler socketClientHandler){
        if(gamesData.get(socketClientHandler.getGameID()) == null) { //if the player is not connected to a game
            socketClientHandler.disconnect();
            return;
        }
        //update the model so every player knows about the reconnection
        ConnectionRestoredExecutor.execute(gamesData.get(socketClientHandler.getGameID()), socketClientHandler.getNickname());
    }
}
