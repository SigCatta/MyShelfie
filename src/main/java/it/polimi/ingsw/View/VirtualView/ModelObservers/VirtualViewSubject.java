package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewObserver;

public interface VirtualViewSubject {
    void registerObserver(VirtualViewObserver observer);
    void removeObserver(VirtualViewObserver observer);
    void notifyObservers();
}
