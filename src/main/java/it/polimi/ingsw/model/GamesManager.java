package it.polimi.ingsw.model;

import java.util.HashMap;

/**
 * Singleton class that contains the games that are being played
 */
public class GamesManager {

    /**
     * map of gameID / game
     */
    private HashMap<Integer, Game> games;

    private static GamesManager gamesManagerInstance;


    private GamesManager(){
        games = new HashMap<>();
    }

    public static GamesManager getInstance(){
        if(gamesManagerInstance == null){
            gamesManagerInstance = new GamesManager();
        }
        return gamesManagerInstance;
    }

    /**
     * Adds a game to the map
     * @return the id of the game
     */
    public int addGame(int numberOfPlayers){
        Game newGame = new Game(numberOfPlayers);

        int gameID = createID();
        newGame.setGameID(gameID);
        games.put(gameID, newGame);

        return gameID;
    }

    /**
     * create a non-existing id
     */
    private int createID(){
        int MAX_VALUE = Integer.MAX_VALUE;

        int id = (int)(Math.random()*MAX_VALUE);

        while(games.get(id) != null){
            if(id == MAX_VALUE){
                id = 0;
            }
            id++;
        }
        return id;
    }

    public void removeGame(int gameID){
        games.remove(gameID);
    }

    public Game getGame(int gameID){
        return games.get(gameID);
    }

    public HashMap<Integer, Game> getGames() {
        return games;
    }
}
