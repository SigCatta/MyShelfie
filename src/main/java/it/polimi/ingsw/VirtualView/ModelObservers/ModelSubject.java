package it.polimi.ingsw.VirtualView.ModelObservers;

public interface ModelSubject {
    void registerObserver(ModelObserver observer);

    void removeObserver(ModelObserver observer);

    void notifyObservers();
}
