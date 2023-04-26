package it.polimi.ingsw.Controller.Client.VirtualModel;

public interface VirtualModelSubject {
    void registerObserver(VirtualModelObserver observer);
    void removeObserver(VirtualModelObserver observer);
    void notifyObservers();
}
