package it.polimi.ingsw.model;

import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.exceptions.TooManyPlayersException;
import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.EndOfTurn.TurnHandler;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.ChosenTilesTable.ChosenTilesTable;
import it.polimi.ingsw.model.cards.commonGoals.CommonCardDealer;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;
import it.polimi.ingsw.model.cards.personalGoals.PersonalCardDealer;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Bag;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Game implements VirtualViewSubject {

    private final ArrayList<VirtualViewObserver> observers;
    private final int BOARD_DIMENSION = 9, MAX_TILES_FROM_BOARD = 3;
    private final int MAX_PLAYER_NUMBER;
    private VirtualView virtualView;
    private int gameID;
    private Bag bag;
    private Board board;
    private final ChosenTilesTable chosenTilesTable;
    private GameState gameState;
    private final ArrayList<CommonGoalCard> commonGoals;

    private final ArrayList<Player> players;
    private Player activePlayer;

    public ArrayList<CommonGoalCard> getCommonGoals() {
        return commonGoals;
    }

    private TurnHandler turnHandler;


    public Game(int MAX_PLAYER_NUMBER) {
        observers = new ArrayList<>();
        this.MAX_PLAYER_NUMBER = MAX_PLAYER_NUMBER;
        commonGoals = (ArrayList<CommonGoalCard>) CommonCardDealer.pickCommonGoalCards(2);
        gameState = GameState.PREGAME;
        players = new ArrayList<>();
        board = new Board(BOARD_DIMENSION);
        chosenTilesTable = new ChosenTilesTable();
    }

    /**
     * Starts the actual game (no more players can connect)
     */
    public void start() {
        System.out.println("the game started!"); // TODO remove
        bag = new Bag();

        turnHandlerInitializer();

        gameState = GameState.PICK_UP_TILES;

        if (virtualView != null) {//TODO this is just for testing
            notifyObservers();
        }

        new BoardRefresher(this).refillBoard();
        if (players.size() > 0) {
            this.activePlayer = players.get(0);

            try {
                PersonalCardDealer.getCards(players);
            } catch (IOException | ParseException | TooManyPlayersException e) {
                virtualView.send(new EchoMTC(EchoID.PANIC, true)); // should never reach
                end();
            }
        }
        notifyObservers();
    }

    /**
     * to be called on creation of the virtual view,
     * this parameter is useful to start the communication with the virtual view
     */
    public void setVirtualView(VirtualView virtualView) {
        this.virtualView = virtualView;
    }

    private void turnHandlerInitializer() {
        turnHandler = new TurnHandler(this);
        turnHandler.attachEndOfTurn(new ScoreBoard(this));
        turnHandler.attachEndOfTurn(new BoardRefresher(this));
    }

    public void end() {
        gameState = GameState.END;
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

    public ChosenTilesTable getChosenTilesTable() {
        return chosenTilesTable;
    }

    public synchronized void addPlayer(Player player) {

        players.add(player);

        System.out.println("the player " + player.getNickname() + " connected successfully to game " + gameID); //TODO remove
        if (players.size() == MAX_PLAYER_NUMBER) start();

        player.getShelf().notifyObservers();
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
        notifyObservers();
    }

    public Player getPlayer(String nickname) {
        for (Player player : players) {
            if (player.getNickname().equals(nickname)) return player;
        }
        return null;// should never reach
    }

    public void disconnectPlayer(String playerNickname) {
        Player player = getPlayer(playerNickname);
        player.setConnected(false);
        players.remove(player);
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
        for (VirtualViewObserver observer : observers) {
            observer.update();
        }
    }
}
