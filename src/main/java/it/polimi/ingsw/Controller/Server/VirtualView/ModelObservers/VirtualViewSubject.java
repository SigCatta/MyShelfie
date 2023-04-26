package it.polimi.ingsw.Controller.Server.VirtualView.ModelObservers;

public interface VirtualViewSubject {
    void registerObserver(VirtualViewObserver observer);
    void removeObserver(VirtualViewObserver observer);
    void notifyObservers();
}
