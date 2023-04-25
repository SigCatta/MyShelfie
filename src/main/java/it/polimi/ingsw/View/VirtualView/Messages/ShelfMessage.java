package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;

public class ShelfMessage implements Message, Serializable {
    private final ItemTile[][] SHELF;
    private final String OWNER;

    public ShelfMessage(Player shelfOwner){
        this.SHELF = shelfOwner.getShelf().getShelfGrid();
        this.OWNER = shelfOwner.getNickname();
    }

    public String getOwner() {
        return OWNER;
    }

    public ItemTile[][] getShelf() {
        return SHELF;
    }

    @Override
    public void update() {
        Controller.getInstance().changeShelf(this);
    }
}
