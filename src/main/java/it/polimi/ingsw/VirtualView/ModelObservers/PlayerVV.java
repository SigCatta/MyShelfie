package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.MessageToClient;
import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

public class PlayerVV implements VirtualViewObserver, MessageToClient, Serializable {

    private final Player PLAYER;
    private final VirtualView VIRTUAL_VIEW;

    public PlayerVV(Player player, VirtualView virtualView){
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
