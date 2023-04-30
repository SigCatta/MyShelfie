package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.ChosenTilesTableMessageToClient;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.List;

public class TilesTableRepresentation implements VirtualModelSubject{

    //TODO do singleton class and finish
    private static TilesTableRepresentation instance;
    private ChosenTilesTableMessageToClient tilesTableMessage;

    private TilesTableRepresentation(){}

    public static TilesTableRepresentation getInstance(){
        if(instance == null) instance = new TilesTableRepresentation();
        return instance;
    }

    public void updateTable(ChosenTilesTableMessageToClient tableMessage){
        this.tilesTableMessage = tableMessage;
        notifyObservers();
    }

    public List<ItemTile> getTiles(){
        return tilesTableMessage.getChosenTiles();
    }

    @Override
    public void registerObserver(VirtualModelObserver observer) {

    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
