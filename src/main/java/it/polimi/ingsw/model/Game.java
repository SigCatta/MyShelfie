package it.polimi.ingsw.model;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.EndOfTurn.TurnHandler;
import it.polimi.ingsw.model.GameState.GameState;
import it.polimi.ingsw.model.GameState.PickUpTilesState;
import it.polimi.ingsw.model.GameState.PregameState;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Bag;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Game {

    private final int BOARD_DIMENSION = 9;
    private final int MAX_TILES_FROM_BOARD = 3;
    private final int MAX_PLAYER_NUMBER;

    private int gameID;
    private Bag bag;
    private Board board;
    private TilesGetter tilesGetter;
    private GameState gameState;

    private ArrayList<Player> players;
    private Player activePlayer;

    private TurnHandler turnHandler;


    public Game(int MAX_PLAYER_NUMBER) {
        this.MAX_PLAYER_NUMBER = MAX_PLAYER_NUMBER;
        gameState = new PregameState();
        players = new ArrayList<>();
        board = new Board(BOARD_DIMENSION);
    }

    public void start() {
        bag = new Bag();

        tilesGetter = new TilesGetter(this);
        turnHandlerInitializer();
        new BoardRefresher(this).refillBoard();

        gameState = new PickUpTilesState();
    }

    private void turnHandlerInitializer() {
        turnHandler = new TurnHandler(this);
        turnHandler.attachEndOfTurn(new ScoreBoard(this));
        turnHandler.attachEndOfTurn(new BoardRefresher(this));
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
        tilesGetter.setActivePlayer(activePlayer);
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

    public boolean addPlayer(Player player) {

        if (players.size() == MAX_PLAYER_NUMBER) {
            //TODO: controller that modifies view and alerts new player that he can't participate
            return false;
        }

        players.add(player);
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

    public Player getPlayerByID(String nickname) {
        for (Player player : players) {
            if (player.getNickname().equals(nickname)) return player;
        }
        return null;// should never reach
    }

    public void disconnectPlayer(String playerNickname) {
        Player player = getPlayerByID(playerNickname);
        if (gameState instanceof PregameState) {
            players.remove(player);
            //TODO send PLAYER_DOWN message
        } else {
            player.setConnected(false);
            //TODO send PLAYER_DWON message
        }
        //TODO start timeout if there is only one player connected
    }

    public void reconnectPlayer(String playerNickname) {
        Player player = getPlayerByID(playerNickname);
        player.setConnected(true);
        //TODO stop timeout
    }
}
