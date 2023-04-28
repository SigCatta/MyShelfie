package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.BoardMessage;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.List;

public class BoardRepresentation implements VirtualModelSubject {
    private List<VirtualModelObserver> observers;
    private static BoardRepresentation instance;
    private Color[][] board;

    private BoardRepresentation() {}

    public static BoardRepresentation getInstance() {
        if (instance == null) instance = new BoardRepresentation();
        return instance;
    }

    public void setBoard(BoardMessage board) {
        this.board = board.getColorBoard();
        notifyObservers();
    }

    public void setBoard(Color[][] board) { // for testing
        this.board = board;
    }

    public Color[][] getBoard() {
        return board;
    }

    @Override
    public void registerObserver(VirtualModelObserver observer) {
        if (observers == null) observers = new ArrayList<>();
        observers.add(observer);
    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {
        if (observers == null) return;
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (VirtualModelObserver observer : observers) {
            observer.update();
        }
    }
}