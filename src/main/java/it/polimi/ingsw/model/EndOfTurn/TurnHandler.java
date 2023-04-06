package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TurnHandler implements EndOfTurnSubject {


    public Game game;
    private List<EndOfTurnObserver> observers;
    private List<Player> players;
    private boolean lastTurn;


    public TurnHandler(Game game) {
        this.game = game;
        this.observers = new ArrayList<>();
        this.players = game.getPlayers();
    }

    public void changeTurn() {
        notifyObservers();

        int nextIndex = players.indexOf(game.getActivePlayer()) + 1;
        nextIndex = nextIndex >= players.size() ? 0 : nextIndex;
        game.setActivePlayer(players.get(nextIndex));

    }


    public boolean isLastTurn() {
        return lastTurn;
    }

    public void startLastTurn() {
        this.lastTurn = true;
    }

    @Override
    public void attachEndOfTurn(EndOfTurnObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (EndOfTurnObserver observer : observers) {
            observer.update();
        }
    }
}
