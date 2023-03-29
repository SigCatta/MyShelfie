package it.polimi.ingsw.model.Game;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.BoardRefresher;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Bag;

import java.util.ArrayList;
import java.util.List;

public class Game implements EndOfTurnObservable {

    private final int BOARD_DIMENSION = 9;
    private final int MAX_TILES_FROM_BOARD = 3;
    private final int MAX_PLAYER_NUMBER = 4;

    private int gameID;
    private final Bag bag;
    private Board board;

    private BoardRefresher boardRefresher;

    private ArrayList<Player> players;
    private Player activePlayer;
    private Player firstPlayer;

    private List<EndOfTurnObserver> endOfTurnObservers = new ArrayList<>();



    public Game() {
        //TODO create instances of the classes used here
        bag = new Bag();
        board = new Board(BOARD_DIMENSION);
        players = new ArrayList<>();
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

    public BoardRefresher getBoardRefresher() {
        return boardRefresher;
    }

    @Override
    public void attachEndOfTurn(EndOfTurnObserver observer) {
        endOfTurnObservers.add(observer);
    }

    @Override
    public void detachEndOfTurn(EndOfTurnObserver observer) {
        endOfTurnObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(EndOfTurnObserver observer : endOfTurnObservers){
            observer.update();
        }
    }
}
