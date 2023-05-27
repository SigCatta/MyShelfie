package it.polimi.ingsw.model.board.ChosenTilesTable;

import it.polimi.ingsw.VirtualView.ModelObservers.ModelObserver;
import it.polimi.ingsw.VirtualView.ModelObservers.ModelSubject;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that contains the ItemTiles that have been chosen to be picked up from the board
 */
public class ChosenTilesTable implements ModelSubject {
    private final List<ModelObserver> observers = new ArrayList<>();

    private final List<ItemTile> chosenTiles = new ArrayList<>();

    /**
     * this attribute is set when the player wants to insert the first tile of chosenTiles into their shelf
     */
    private Integer chosenColumn;

    public void addTiles(ArrayList<ItemTile> tiles) {
        chosenTiles.addAll(tiles);
        chosenColumn = null;
        notifyObservers();
    }

    public ItemTile popTile(int index) {
        ItemTile chosenTile = chosenTiles.remove(index);
        notifyObservers();
        return chosenTile;
    }

    public int size() {
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
    public void registerObserver(ModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(ModelObserver::update);
    }
}
