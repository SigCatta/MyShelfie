package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the turn handler of the game.
 * This class manages the turns of the players and notifies the observers when a turn ends.
 */
public class TurnHandler implements EndOfTurnSubject {

    /**
     * The game associated with this turn handler.
     */
    public final Game game;

    /**
     * The list of observers subscribed to notifications when a turn ends.
     */
    private final List<EndOfTurnObserver> observers;

    /**
     * The list of players in the game.
     */
    private final List<Player> players;

    /**
     * A flag indicating whether the current turn is the last one of the game.
     */
    private boolean lastTurn;

    /**
     * Constructs a new TurnHandler object with the given game.
     *
     * @param game the game to be associated with this turn handler.
     */
    public TurnHandler(Game game) {
        this.game = game;
        this.observers = new ArrayList<>();
        this.players = game.getPlayers();
    }

    /**
     * Changes the active player to the next one in the list of players.
     * Notifies the observers that the current turn has ended.
     */
    public void changeTurn() {

        int currentIndex = players.indexOf(game.getActivePlayer());
        int nextIndex = currentIndex;

        if (players.get(currentIndex).getShelf().isFull()) {
            lastTurn = true;
            game.getVirtualView().send(new EchoMTC(EchoID.LAST_TURN, false));
        }

        nextIndex = nextIndex + 1 >= players.size() ? 0 : nextIndex + 1;

        if (lastTurn && nextIndex == 0) game.end();

        while (!players.get(nextIndex).isConnected()) {
            nextIndex = nextIndex + 1 >= players.size() ? 0 : nextIndex + 1;
            if (nextIndex == currentIndex) { //end game for lack of connected players
                game.end();
            }
        }

        game.setActivePlayer(players.get(nextIndex));

        notifyObservers();
    }

    /**
     * Sets the flag indicating that the current turn is the last one of the game.
     */
    public void startLastTurn() {
        this.lastTurn = true;
    }

    /**
     * Attaches an observer to this subject to be notified when a turn ends.
     *
     * @param observer the observer to be attached.
     */
    @Override
    public void attachEndOfTurn(EndOfTurnObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers subscribed to this subject that a turn has ended.
     */
    @Override
    public void notifyObservers() {
        observers.forEach(EndOfTurnObserver::update);
    }
}