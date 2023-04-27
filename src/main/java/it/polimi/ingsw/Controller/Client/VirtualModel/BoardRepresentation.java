package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.BoardMessage;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.List;

public class BoardRepresentation implements VirtualModelSubject {
    private List<VirtualModelObserver> observers;
    private static BoardRepresentation instance;
    private ItemTile[][] board;

    private BoardRepresentation() {}

    public static BoardRepresentation getInstance() {
        if (instance == null) instance = new BoardRepresentation();
        return instance;
    }

    public void setBoard(BoardMessage board) {
        this.board = board.getBoard();
        notifyObservers();
    }

    public void setBoard(ItemTile[][] board) { /** for testing */
        this.board = board;
    }

    public ItemTile[][] getBoard() {
        return board;
    }

    public Color[][] getBoardForCLI() {
        Color[][] colors = new Color[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                colors[i][j] = board[i][j] == null ? null : board[i][j].getColor();
            }
        }
        //TODO might be more efficient to delete the double for loop and return the matrix [[]...[]]
        return colors;
    }

    //TODO method that retrieves the board

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