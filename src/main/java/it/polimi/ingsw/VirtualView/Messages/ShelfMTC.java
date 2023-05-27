package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This class represents a message to the client containing information about a player's shelf in the game.
 * It provides details such as the owner's nickname, the item tiles present in the shelf,
 * and a representation of the shelf for the CLI.
 */
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
        return Arrays.stream(SHELF)
                .map(row -> Arrays.stream(row)
                        .map(itemTile -> itemTile == null ? null : itemTile.getColor())
                        .toArray(Color[]::new))
                .toArray(Color[][]::new);
    }

    @Override
    public void update() {
        ShelvesRepresentation.getInstance().updateShelf(this);
    }
}
