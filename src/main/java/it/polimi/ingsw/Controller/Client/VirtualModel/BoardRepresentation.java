package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.BoardMessage;
import it.polimi.ingsw.model.tiles.ItemTile;

public class BoardRepresentation extends SingletonImplementation implements VirtualModelSubject{
    private ItemTile[][] board;

    private BoardRepresentation() {}
    public static BoardRepresentation getInstance() {
        return getInstance(BoardRepresentation.class);
    }

    public void setBoard(BoardMessage board){
        this.board = board.getBoard();
        notifyObservers();
    }

    public ItemTile[][] getBoard() {
        return board;
    }

    //TODO method that retrieves the board

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