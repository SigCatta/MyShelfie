package it.polimi.ingsw.model;

import it.polimi.ingsw.Controller.Server.GamesManager;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.VirtualView.ModelObservers.CommonGoalVV;
import it.polimi.ingsw.VirtualView.ModelObservers.ModelObserver;
import it.polimi.ingsw.VirtualView.ModelObservers.ModelSubject;
import it.polimi.ingsw.VirtualView.VirtualView;
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

public class Game implements ModelSubject {

    private final ArrayList<ModelObserver> observers;
    private final int BOARD_DIMENSION = 9;
    private final int MAX_PLAYER_NUMBER;
    private VirtualView virtualView;
    private int gameID;
    private Bag bag;
    private final Board board;
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
        commonGoals = (ArrayList<CommonGoalCard>) CommonCardDealer.pickCommonGoalCards(2, MAX_PLAYER_NUMBER);
        gameState = GameState.PREGAME;
        players = new ArrayList<>();
        board = new Board(BOARD_DIMENSION);
        chosenTilesTable = new ChosenTilesTable();
    }

    /**
     * Starts the actual game (no more players can connect)
     */
    public void start() {
        bag = new Bag();

        turnHandlerInitializer();

        gameState = GameState.PICK_UP_TILES;

        new BoardRefresher(this).refillBoard();
        if (players.size() > 0) {
            this.activePlayer = players.get(0);

            try {
                PersonalCardDealer.getCards(players);
            } catch (IOException | ParseException | ArrayIndexOutOfBoundsException e) {
                virtualView.send(new EchoMTC(EchoID.PANIC, true));
                end();
            }
        }
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
        turnHandler.attachEndOfTurn(new CommonGoalVV(this, this.virtualView));
    }

    public void end() {
        gameState = GameState.END;
        notifyObservers();
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

    public ChosenTilesTable getChosenTilesTable() {
        return chosenTilesTable;
    }

    public synchronized void addPlayer(Player player) {

        players.add(player);

        System.out.println("the player " + player.getNickname() + " connected successfully to game " + gameID);
        if (players.size() == MAX_PLAYER_NUMBER) start();

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
        return players.stream().filter(p -> p.getNickname().equals(nickname)).findFirst().orElse(null);
    }

    /**
     * Disconnects the player from the game, also terminates
     * the game if players are disconnected
     *
     * @param playerNickname player to disconnect
     */
    public void disconnectPlayer(String playerNickname) {
        getPlayer(playerNickname).setConnected(false);

        for (Player p : players) {
            if (p.isConnected()) return;
        }
        GamesManager.getInstance().endGame(gameID);
    }

    public GameState getGameState() {
        return gameState;
    }

    public VirtualView getVirtualView() {
        return virtualView;
    }

    @Override
    public void registerObserver(ModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.update();
        }
    }
}
