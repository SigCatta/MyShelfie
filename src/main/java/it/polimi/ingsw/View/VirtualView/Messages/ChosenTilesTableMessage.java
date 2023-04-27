package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;
import java.util.List;

public class ChosenTilesTableMessage implements Message, Serializable {
    private final List<ItemTile> CHOSEN_TILES;

    public ChosenTilesTableMessage(List<ItemTile> chosenTiles){
        this.CHOSEN_TILES = chosenTiles;
    }

    public List<ItemTile> getChosenTiles() {
        return CHOSEN_TILES;
    }

    @Override
    public void update() {
        Controller.getInstance().changeTilesTable(this);
    }
}
