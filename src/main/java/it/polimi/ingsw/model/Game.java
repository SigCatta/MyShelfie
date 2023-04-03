package it.polimi.ingsw.model;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.EndOfTurn.TurnHandler;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
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
    private TilesGetter tilesGetter;

    private ArrayList<Player> players;
    private Player activePlayer;
    private Player firstPlayer;

    private TurnHandler turnHandler;


    public Game() throws TooManyCardsRequestedException {
        //TODO create instances of the classes used here
        bag = new Bag();
        board = new Board(BOARD_DIMENSION);
        players = new ArrayList<>();
        turnHandler = new TurnHandler(this);
        tilesGetter = new TilesGetter(this);
        //TODO insert players in the list, if it is not done here there boardRefresher won't work
        new BoardRefresher(this);
        new ScoreBoard(this);
        tilesGetter = new TilesGetter(this);
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
        scoreBoard.setActivePlayer(activePlayer);
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


    public boolean addPlayer(Player player)  {

        if(players.size() == MAX_PLAYER_NUMBER) {
            //TODO: controller that modifies view and alerts new player that he can't participate
            return false;
        }

        players.add(player);
        return true;
    }

    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }
}
