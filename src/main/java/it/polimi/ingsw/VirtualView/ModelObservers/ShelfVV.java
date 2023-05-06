package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.MessageToClient;
import it.polimi.ingsw.VirtualView.Messages.ShelfMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

public class ShelfVV implements VirtualViewObserver, MessageToClient, Serializable {
    private final VirtualView VIRTUAL_VIEW;
    private final Player OWNER;

    public ShelfVV(Player player, VirtualView virtualView){
        this.OWNER = player;
        this.VIRTUAL_VIEW = virtualView;
        player.getShelf().registerObserver(this);
    }
    @Override
    public void update() {
        VIRTUAL_VIEW.send(new ShelfMTC(OWNER));
    }
}
