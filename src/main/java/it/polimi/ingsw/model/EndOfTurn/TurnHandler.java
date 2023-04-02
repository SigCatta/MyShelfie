package it.polimi.ingsw.model.EndOfTurn;

import exceptions.NullItemTileException;
import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.Game;

import java.util.ArrayList;
import java.util.List;

public class TurnHandler implements EndOfTurnSubject{


    public Game game;
    private List<EndOfTurnObserver> observers;


    public TurnHandler(Game game) throws TooManyCardsRequestedException {
        this.game = game;
        this.observers = new ArrayList<>();
        game.setScoreBoard(new ScoreBoard(game));
        this.observers.add(game.getScoreBoard());
        //TODO set the observer
    }

    public void changeTurn() throws NullItemTileException {
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
    public void notifyObservers() throws NullItemTileException {
        for(EndOfTurnObserver observer : observers){
            observer.update();
        }
    }
}
