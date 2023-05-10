package it.polimi.ingsw.View.GUI.GuiObservers;

import java.util.ArrayList;
import java.util.List;

public abstract class GuiObservable {
    private List<GuiObserver> observers = new ArrayList<>();

    public void addObserver(GuiObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GuiObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (GuiObserver observer : observers) {
            observer.update(this);
        }
    }
}
