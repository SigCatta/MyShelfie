package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualView.Messages.BoardMTC;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.List;

public class BoardRepresentation implements VirtualModelSubject {
    private List<VirtualModelObserver> observers;
    private ItemTile[][] board;
    private static BoardRepresentation instance;

    private BoardRepresentation() {
        observers = new ArrayList<>();
    }

    public static BoardRepresentation getInstance() {
        if (instance == null) instance = new BoardRepresentation();
        return instance;
    }

    public void setBoard(BoardMTC board) {
        this.board = board.getColorBoard();
        notifyObservers();
    }

    public void setBoard(ItemTile[][] board) { // for testing
        this.board = board;
    }

    public ItemTile[][] getBoard() {
        return board;
    }

    public Color[][] getBoardColors() {
        Color[][] colorBoard = new Color[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                colorBoard[i][j] = board[i][j] == null ? null : board[i][j].getColor();
            }
        }
        return colorBoard;
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
        synchronized (this) {
            notifyAll();
        }
    }
}