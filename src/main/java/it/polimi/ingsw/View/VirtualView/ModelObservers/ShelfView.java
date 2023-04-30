package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.MessageToClient;
import it.polimi.ingsw.View.VirtualView.Messages.ShelfMessageToClient;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

public class ShelfView implements VirtualViewObserver, MessageToClient, Serializable {
    private final VirtualView VIRTUAL_VIEW;
    private final Player OWNER;

    public ShelfView(Player player, VirtualView virtualView){
        this.OWNER = player;
        this.VIRTUAL_VIEW = virtualView;
        player.getShelf().registerObserver(this);
    }
    @Override
    public void update() {
        VIRTUAL_VIEW.send(new ShelfMessageToClient(OWNER));
    }
}
