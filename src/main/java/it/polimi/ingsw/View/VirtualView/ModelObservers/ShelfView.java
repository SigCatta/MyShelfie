package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.Message;
import it.polimi.ingsw.View.VirtualView.Messages.ShelfMessage;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.player.Shelf;

import java.io.Serializable;

public class ShelfView implements VirtualViewObserver, Message, Serializable {
    private final VirtualView VIRTUAL_VIEW;
    private final Shelf SHELF;

    public ShelfView(Shelf shelf, VirtualView virtualView){
        this.SHELF = shelf;
        this.VIRTUAL_VIEW = virtualView;
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new ShelfMessage(SHELF));
    }
}
