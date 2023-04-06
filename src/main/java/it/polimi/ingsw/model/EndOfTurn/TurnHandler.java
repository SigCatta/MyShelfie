package it.polimi.ingsw.model.EndOfTurn;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the turn handler in the game.
 * It is responsible for changing the active player and notifying the observers when a turn ends.
 */
public class TurnHandler implements EndOfTurnSubject {

    /**
     * The game object.
     */
    public Game game;

    /**
     * The list of observers to notify at the end of a turn.
     */
    private List<EndOfTurnObserver> observers;

    /**
     * The list of players in the game.
     */
    private List<Player> players;

    /**
     * Constructs a turn handler object with the given game object.
     *
     * @param game the game object.
     */
    public TurnHandler(Game game) {
        this.game = game;
        this.observers = new ArrayList<>();
        this.players = game.getPlayers();
        //TODO set the observer
    }

    /**
     * Changes the turn by notifying the observers and setting the next active player.
     */
    public void changeTurn() {
        notifyObservers();

        int nextIndex = players.indexOf(game.getActivePlayer()) + 1;
        nextIndex = nextIndex >= players.size() ? 0 : nextIndex;
        game.setActivePlayer(players.get(nextIndex));
    }

    /**
     * Attaches an observer to the list of observers.
     *
     * @param observer the observer to attach.
     */
    @Override
    public void attachEndOfTurn(EndOfTurnObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers in the list of observers.
     */
    @Override
    public void notifyObservers() {
        for(EndOfTurnObserver observer : observers){
            observer.update();
        }
    }
}