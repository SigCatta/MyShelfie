package it.polimi.ingsw.model.EndOfTurn;

public interface EndOfTurnSubject {
    void attachEndOfTurn(EndOfTurnObserver observer);

    void notifyObservers();
}
