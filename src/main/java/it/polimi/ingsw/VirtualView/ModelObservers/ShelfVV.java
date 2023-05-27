package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.ShelfMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

/**
 * The ShelfVV class is a model observer that listens for changes in the player's shelf
 * and sends an updated shelf state to the clients.
 */
public class ShelfVV implements ModelObserver, Serializable {
    private final VirtualView VIRTUAL_VIEW;
    private final Player OWNER;

    /**
     * Constructs a ShelfVV object with the specified player and virtual view.
     * It registers itself as an observer of the player's shelf.
     *
     * @param player      the player object representing the player
     * @param virtualView the virtual view object used to send messages to the clients
     */
    public ShelfVV(Player player, VirtualView virtualView) {
        this.OWNER = player;
        this.VIRTUAL_VIEW = virtualView;
        player.getShelf().registerObserver(this);
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new ShelfMTC(OWNER));
    }
}
