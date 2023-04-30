package it.polimi.ingsw.model;

import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessageToClient;
import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.EndOfTurn.TurnHandler;
import it.polimi.ingsw.model.GameState.EndGameState;
import it.polimi.ingsw.model.GameState.GameState;
import it.polimi.ingsw.model.GameState.PickUpTilesState;
import it.polimi.ingsw.model.GameState.PregameState;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Bag;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Game implements VirtualViewSubject {

    private ArrayList<VirtualViewObserver> observers;

    private final int BOARD_DIMENSION = 9;
    private final int MAX_TILES_FROM_BOARD = 3;
    private final int MAX_PLAYER_NUMBER;
    private VirtualView virtualView;
    private int gameID;
    private Bag bag;
    private Board board;
    private TilesGetter tilesGetter;
    private GameState gameState;

    private ArrayList<Player> players;
    private Player activePlayer;

    private TurnHandler turnHandler;


    public Game(int MAX_PLAYER_NUMBER) {
        observers = new ArrayList<>();
        this.MAX_PLAYER_NUMBER = MAX_PLAYER_NUMBER;
        gameState = new PregameState();
        players = new ArrayList<>();
        board = new Board(BOARD_DIMENSION);
    }

    /**
     * Starts the actual game (no more players can connect)
     */
    public void start() {
        bag = new Bag();

        tilesGetter = new TilesGetter(this);
        turnHandlerInitializer();

        gameState = new PickUpTilesState();

        if(virtualView != null) {//TODO this is just for testing
            virtualView.observersInit(); //the virtual view attribute has been set from the GamesManager
            notifyObservers();
        }

        new BoardRefresher(this).refillBoard();
    }

    /**
     * to be called on creation of the virtual view,
     * this parameter is useful to start the communication with the virtual view
     */
    public void setVirtualView(VirtualView virtualView){
        this.virtualView = virtualView;
    }

    private void turnHandlerInitializer() {
        turnHandler = new TurnHandler(this);
        turnHandler.attachEndOfTurn(new ScoreBoard(this));
        turnHandler.attachEndOfTurn(new BoardRefresher(this));
    }

    public void end(){
        gameState = new EndGameState();
        //TODO calculate point and send to the view
    }


    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getMAX_TILES_FROM_BOARD() {
        return MAX_TILES_FROM_BOARD;
    }

    public TilesGetter getTilesGetter() {
        return tilesGetter;
    }

    public synchronized boolean addPlayer(Player player) {

        if (players.size() >= MAX_PLAYER_NUMBER) {
            return false;
        }

        players.add(player);

        System.out.println("the player " + player.getNickname() + " connected successfully"); //TODO remove
        return true;
    }

    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    /**
     * Checks which player has the highest score and returns it. If two or more players have the same score,
     * the winner is the one who played last
     *
     * @return the winner of the game
     */
    public Player getWinner() throws NoSuchElementException {
        //noinspection ComparatorMethodParameterNotUsed
        return players.stream().max((p1, p2) -> p1.getScore() > p2.getScore() ? 1 : -1).orElse(null);
    }

    public int getMAX_PLAYER_NUMBER() {
        return MAX_PLAYER_NUMBER;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return this.gameID;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getPlayer(String nickname) {
        for (Player player : players) {
            if (player.getNickname().equals(nickname)) return player;
        }
        return null;// should never reach
    }

    public void disconnectPlayer(String playerNickname) {
        Player player = getPlayer(playerNickname);
        players.remove(player);

        //TODO start timeout if there is only one player connected
    }

    public void reconnectPlayer(String playerNickname) {
        Player player = getPlayer(playerNickname);
        player.setConnected(true);
        //TODO stop timeout
    }
        
    public GameState getGameState() {
        return gameState;
    }


    public VirtualView getVirtualView() {
        return virtualView;
    }

    @Override
    public void registerObserver(VirtualViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(VirtualViewObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(VirtualViewObserver observer : observers){
            observer.update();
        }
    }
}
