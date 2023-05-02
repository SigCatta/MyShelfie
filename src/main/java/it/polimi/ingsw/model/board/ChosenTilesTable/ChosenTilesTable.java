package it.polimi.ingsw.model.board.ChosenTilesTable;

import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.List;

public class ChosenTilesTable implements VirtualViewSubject {
    private final List<VirtualViewObserver> observers = new ArrayList<>();

    private List<ItemTile> chosenTiles = new ArrayList<>();

    private Board board;
    private Integer chosenColumn;

    public void addTiles(ArrayList<ItemTile> tiles){
        chosenTiles.addAll(tiles);
        chosenColumn = null;
        notifyObservers();
    }

    public ItemTile popTile(int index){
        ItemTile chosenTile = chosenTiles.remove(index);
        notifyObservers();
        return chosenTile;
    }

    public ItemTile getTile(int index){
        return chosenTiles.get(index);
    }

    public int size(){
        return chosenTiles.size();
    }

    public Integer getChosenColumn() {
        return chosenColumn;
    }

    public void setChosenColumn(Integer chosenColumn) {
        this.chosenColumn = chosenColumn;
    }

    public List<ItemTile> getChosenTiles() {
        return chosenTiles;
    }

    @Override
    public void registerObserver(VirtualViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(VirtualViewObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(VirtualViewObserver observer : observers){
            observer.update();
        }
    }
}
