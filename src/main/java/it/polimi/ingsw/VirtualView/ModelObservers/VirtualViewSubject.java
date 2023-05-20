package it.polimi.ingsw.VirtualView.ModelObservers;

public interface VirtualViewSubject {
    void registerObserver(VirtualViewObserver observer);

    void removeObserver(VirtualViewObserver observer);

    void notifyObservers();
}
