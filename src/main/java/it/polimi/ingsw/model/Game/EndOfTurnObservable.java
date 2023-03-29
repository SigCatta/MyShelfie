package it.polimi.ingsw.model.Game;

public interface EndOfTurnObservable {
    void attachEndOfTurn(EndOfTurnObserver observer);
    void detachEndOfTurn(EndOfTurnObserver observer);
    void notifyObservers();
}
