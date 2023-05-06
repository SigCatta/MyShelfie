package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;

public class ShelfMTC implements MessageToClient, Serializable {
    private final ItemTile[][] SHELF;
    private final String OWNER;

    public ShelfMTC(Player shelfOwner) {
        this.SHELF = shelfOwner.getShelf().getShelfGrid();
        this.OWNER = shelfOwner.getNickname();
    }

    public String getOwner() {
        return OWNER;
    }

    public ItemTile[][] getShelf() {
        return SHELF;
    }

    public Color[][] getShelfForCLI() {
        Color[][] colors = new Color[SHELF.length][SHELF[0].length];
        for (int i = 0; i < SHELF.length; i++) {
            for (int j = 0; j < SHELF[0].length; j++) {
                colors[i][j] = SHELF[i][j] == null ? null : SHELF[i][j].getColor();
            }
        }
        //TODO might be more efficient to delete the double for loop and return the matrix [[]...[]]
        return colors;
    }

    @Override
    public void update() {
        ShelvesRepresentation.getInstance().updateShelf(this);
    }
}
