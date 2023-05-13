package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.Messages.GameMTC;

import java.util.ArrayList;

public class GameRepresentation implements VirtualModelSubject {
    private GameMTC gameMessage;
    private final ArrayList<VirtualModelObserver> observers;
    private static GameRepresentation instance;

    private GameRepresentation() {
        observers = new ArrayList<>();
    }

    public static GameRepresentation getInstance() {
        if (instance == null) instance = new GameRepresentation();
        return instance;
    }

    public void setGame(GameMTC gameMessage) {
        this.gameMessage = gameMessage;
        notifyObservers();
    }


    public int getGameID() {
        return gameMessage.getGameID();
    }

    public GameState getGameState() {
        return gameMessage.getGameState();
    }

    public String getActivePlayerNickname() {
        return gameMessage.getActivePlayerNickname();
    }

    public GameMTC getGameMessage() {
        return gameMessage;
    }

    @Override
    public void registerObserver(VirtualModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(VirtualModelObserver::update);
        synchronized (this){
            notifyAll();
        }
    }
}