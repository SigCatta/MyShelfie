package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.Message;
import it.polimi.ingsw.View.VirtualView.Messages.ShelfMessage;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

import java.io.Serializable;

public class ShelfView implements VirtualViewObserver, Message, Serializable {
    private final VirtualView VIRTUAL_VIEW;
    private final String OWNER;
    private final Shelf SHELF;

    public ShelfView(Player player, VirtualView virtualView){
        this.SHELF = player.getShelf();
        this.OWNER = player.getNickname();
        this.VIRTUAL_VIEW = virtualView;
    }
    @Override
    public void update() {
        VIRTUAL_VIEW.send(new ShelfMessage(SHELF));
    }

    public VirtualView getVIRTUAL_VIEW() {
        return VIRTUAL_VIEW;
    }

    public Shelf getSHELF() {
        return SHELF;
    }

    public String getOWNER() {
        return OWNER;
    }
}
