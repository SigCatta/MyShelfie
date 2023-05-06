package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.GameMTC;

public class GameRepresentation implements VirtualModelSubject{
    private GameMTC gameMessage;

    private static GameRepresentation instance;

    private GameRepresentation() {}

    public static GameRepresentation getInstance() {
        if (instance == null) instance = new GameRepresentation();
        return instance;
    }

    public void setGame(GameMTC gameMessage){
        this.gameMessage = gameMessage;
        notifyObservers();
    }

    public GameMTC getGameMessage() {
        return gameMessage;
    }

    @Override
    public void registerObserver(VirtualModelObserver observer) {

    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {

    }

    @Override
    public void notifyObservers() {
        synchronized (this){
            this.notifyAll();
        }
    }
}