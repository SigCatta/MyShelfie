package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TurnHandler implements EndOfTurnSubject{


    private Game game;
    private List<EndOfTurnObserver> observers = new ArrayList<>();
    private List<Player> players;


    public TurnHandler(Game game){
        this.game = game;
        players = game.getPlayers();
    }

    public void changeTurn(){
        notifyObservers();

        int nextIndex = players.indexOf(game.getActivePlayer()) + 1;
        nextIndex = nextIndex >= players.size() ? 0 : nextIndex;
        game.setActivePlayer(players.get(nextIndex));

    }

    @Override
    public void attachEndOfTurn(EndOfTurnObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachEndOfTurn(EndOfTurnObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(EndOfTurnObserver observer : observers){
            observer.update();
        }
    }
}
