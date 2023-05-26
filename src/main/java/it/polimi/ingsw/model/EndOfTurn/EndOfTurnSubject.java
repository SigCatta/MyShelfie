package it.polimi.ingsw.model.EndOfTurn;

/**
 * The interface of the subject observed by an EndOfTurnObserver
 */
public interface EndOfTurnSubject {
    void attachEndOfTurn(EndOfTurnObserver observer);

    void notifyObservers();
}
