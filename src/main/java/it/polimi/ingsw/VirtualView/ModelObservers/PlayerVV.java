package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

/**
 * The PlayerVV class is a model observer that listens for changes in the player state
 * and sends an updated player state to the clients.
 */
public class PlayerVV implements ModelObserver, Serializable {

    private final Player PLAYER;
    private final VirtualView VIRTUAL_VIEW;

    /**
     * Constructs a PlayerVV object with the specified player and virtual view.
     * It registers itself as an observer of the player.
     * It also sends an initial player state to the clients.
     *
     * @param player      the player object representing the player
     * @param virtualView the virtual view object used to send messages to the clients
     */
    public PlayerVV(Player player, VirtualView virtualView) {
        this.PLAYER = player;
        this.VIRTUAL_VIEW = virtualView;
        PLAYER.registerObserver(this);
        update();
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new PlayerMTC(PLAYER));
    }
}
