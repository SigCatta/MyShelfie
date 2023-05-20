package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.ChosenTilesTableMTC;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.List;

public class TilesTableRepresentation extends VirtualModelSubject {

    private static TilesTableRepresentation instance;
    private ChosenTilesTableMTC tilesTableMessage;

    private TilesTableRepresentation() {
        super();
    }

    public static TilesTableRepresentation getInstance() {
        if (instance == null) instance = new TilesTableRepresentation();
        return instance;
    }

    public void updateTable(ChosenTilesTableMTC tableMessage) {
        this.tilesTableMessage = tableMessage;
        notifyObservers();
    }

    public List<ItemTile> getTiles() {
        if (tilesTableMessage == null) return null;
        return tilesTableMessage.getChosenTiles();
    }
}
