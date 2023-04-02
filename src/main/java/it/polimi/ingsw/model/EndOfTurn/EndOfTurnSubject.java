package it.polimi.ingsw.model.EndOfTurn;

import exceptions.NullItemTileException;

public interface EndOfTurnSubject {
    void attachEndOfTurn(EndOfTurnObserver observer);

    void detachEndOfTurn(EndOfTurnObserver observer);

    void notifyObservers() throws NullItemTileException;
}
