package it.polimi.ingsw.VirtualModel;

import java.util.ArrayList;
import java.util.List;

public abstract class VirtualModelSubject {
    protected final List<VirtualModelObserver> observers;

    public VirtualModelSubject() {
        observers = new ArrayList<>();
    }
    public synchronized void registerObserver(VirtualModelObserver observer) {
        observers.add(observer);
    }
    public synchronized void removeObserver(VirtualModelObserver observer){
        observers.remove(observer);
    }
    public abstract void notifyObservers();
}
