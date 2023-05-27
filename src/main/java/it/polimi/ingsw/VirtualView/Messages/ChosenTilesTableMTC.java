package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.VirtualModel.TilesTableRepresentation;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;
import java.util.List;

/**
 * This class represents a message to the client containing the current chosen tiles on the table.
 */
public class ChosenTilesTableMTC implements MessageToClient, Serializable {
    private final List<ItemTile> CHOSEN_TILES;

    public ChosenTilesTableMTC(List<ItemTile> chosenTiles) {
        this.CHOSEN_TILES = chosenTiles;
    }

    public List<ItemTile> getChosenTiles() {
        return CHOSEN_TILES;
    }

    @Override
    public void update() {
        TilesTableRepresentation.getInstance().updateTable(this);
    }
}
