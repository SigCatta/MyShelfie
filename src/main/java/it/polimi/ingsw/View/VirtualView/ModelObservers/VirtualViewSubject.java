package it.polimi.ingsw.View.VirtualView.ModelObservers;

public interface VirtualViewSubject {
    void registerObserver(VirtualViewObserver observer);
    void removeObserver(VirtualViewObserver observer);
    void notifyObservers();
}
