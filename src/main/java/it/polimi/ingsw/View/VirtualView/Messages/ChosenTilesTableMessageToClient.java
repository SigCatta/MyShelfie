package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;
import java.util.List;

public class ChosenTilesTableMessageToClient implements MessageToClient, Serializable {
    private final List<ItemTile> CHOSEN_TILES;

    public ChosenTilesTableMessageToClient(List<ItemTile> chosenTiles){
        this.CHOSEN_TILES = chosenTiles;
    }

    public List<ItemTile> getChosenTiles() {
        return CHOSEN_TILES;
    }

    @Override
    public void update() {
        ClientController.getInstance().changeTilesTable(this);
    }
}
