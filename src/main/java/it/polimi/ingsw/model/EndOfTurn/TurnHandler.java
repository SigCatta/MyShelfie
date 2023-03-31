package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.model.EndOfTurn.BoardRefresher.BoardRefresher;
import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TurnHandler implements EndOfTurnSubject{


    public Game game;
    private List<EndOfTurnObserver> observers = new ArrayList<>();


    public TurnHandler(Game game){
        this.game = game;

        new BoardRefresher(game);
        new ScoreBoard(game);
    }

    public void changeTurn(){
        notifyObservers();
        //TODO change player turn
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
