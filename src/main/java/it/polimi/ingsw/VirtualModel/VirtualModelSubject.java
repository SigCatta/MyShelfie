package it.polimi.ingsw.VirtualModel;

public interface VirtualModelSubject {
    void registerObserver(VirtualModelObserver observer);
    void removeObserver(VirtualModelObserver observer);
    void notifyObservers();
}
