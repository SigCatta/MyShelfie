package it.polimi.ingsw.model.board.TilesGetter;

import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.List;

public class ChosenTilesTable implements VirtualViewSubject {
    private final List<VirtualViewObserver> observers = new ArrayList<>();

    private final List<ItemTile> CHOSEN_TILES = new ArrayList<>();

    public void insertTile(ItemTile itemTile){
        CHOSEN_TILES.add(itemTile);
        notifyObservers();
    }
    public ItemTile popTile(int index){
        ItemTile chosenTile = CHOSEN_TILES.remove(index);
        notifyObservers();
        return chosenTile;
    }

    public ItemTile getTile(int index){
        return CHOSEN_TILES.get(index);
    }

    public int size(){
        return CHOSEN_TILES.size();
    }

    public List<ItemTile> getCHOSEN_TILES() {
        return CHOSEN_TILES;
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
