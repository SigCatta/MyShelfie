package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;

public class ShelfMessage implements Message, Serializable {
    private final ItemTile[][] SHELF;

    public ShelfMessage(Shelf shelf){
        this.SHELF = shelf.getShelfGrid();
    }

    public ItemTile[][] getShelf() {
        return SHELF;
    }

    @Override
    public void update() {
        Controller.getInstance().changeShelf(this);
    }
}
