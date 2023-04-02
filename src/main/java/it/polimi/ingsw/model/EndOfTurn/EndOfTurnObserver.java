package it.polimi.ingsw.model.EndOfTurn;

import exceptions.NullItemTileException;

public interface EndOfTurnObserver {
    void update() throws NullItemTileException;
}
