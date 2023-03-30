package it.polimi.ingsw.model;

import it.polimi.ingsw.model.observers.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.observers.EndOfTurn.TurnHandler;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.observers.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.observers.ShelfObserver;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Bag;

import java.util.ArrayList;

public class Game {

    private final int BOARD_DIMENSION = 9;
    private final int MAX_TILES_FROM_BOARD = 3;
    private final int MAX_PLAYER_NUMBER = 4;

    private int gameID;
    private final Bag bag;
    private Board board;

    private ScoreBoard scoreBoard;

    private BoardRefresher boardRefresher;
    private TilesGetter tilesGetter;

    ShelfObserver shelfObserver;
    private ArrayList<Player> players;
    private Player activePlayer;
    private Player firstPlayer;

    private TurnHandler turnHandler;


    public Game() {
        //TODO create instances of the classes used here
        bag = new Bag();
        board = new Board(BOARD_DIMENSION);
        players = new ArrayList<>();
        turnHandler = new TurnHandler(this);
        tilesGetter = new TilesGetter(this);
        scoreBoard = new ScoreBoard(this);
        shelfObserver = new ShelfObserver(this);
        //TODO insert players in the list, if it is not done here there boardRefresher won't work
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

    public Player getFirstPlayer() {
        return firstPlayer;
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


    public void addPlayer(Player player){

        if(players.size() > MAX_PLAYER_NUMBER) return;

        players.add(player);
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public BoardRefresher getBoardRefresher() {
        return boardRefresher;
    }


    public TurnHandler getTurnHandler() {
        return turnHandler;
    }
}
