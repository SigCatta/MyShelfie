package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.model.Game;

import java.util.ArrayList;
import java.util.List;

public class TurnHandler implements EndOfTurnSubject{


    public Game game;
    private List<EndOfTurnObserver> observers = new ArrayList<>();


    public TurnHandler(Game game){
        this.game = game;

        //TODO set the observer
    }

    public void changeTurn(){
        notifyObservers();
        //TODO: change player turn
        //TODO: update Game activePlayer
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
