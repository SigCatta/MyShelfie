package it.polimi.ingsw.model.EndOfTurn;

/**
 * The interface representing an observer that manages the actions
 * that needs to happen at the end of every player turn
 */
public interface EndOfTurnObserver {
    void update();
}
