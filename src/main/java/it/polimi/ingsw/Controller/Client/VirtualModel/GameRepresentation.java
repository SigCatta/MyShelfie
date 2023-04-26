package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.GameMessage;

public class GameRepresentation extends SingletonImplementation implements VirtualModelSubject{
    private GameMessage gameMessage;

    private GameRepresentation() {}
    public static GameRepresentation getInstance() {
        return getInstance(GameRepresentation.class);
    }

    public void setGame(GameMessage gameMessage){
        this.gameMessage = gameMessage;
    }

    public GameMessage getGameMessage() {
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