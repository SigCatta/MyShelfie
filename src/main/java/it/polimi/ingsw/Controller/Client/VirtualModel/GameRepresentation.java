package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.GameMessageToClient;

public class GameRepresentation implements VirtualModelSubject{
    private GameMessageToClient gameMessage;

    private static GameRepresentation instance;

    private GameRepresentation() {}

    public static GameRepresentation getInstance() {
        if (instance == null) instance = new GameRepresentation();
        return instance;
    }

    public void setGame(GameMessageToClient gameMessage){
        this.gameMessage = gameMessage;
    }

    public GameMessageToClient getGameMessage() {
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
        //TODO every observer must be notified when the class changes
    }
}