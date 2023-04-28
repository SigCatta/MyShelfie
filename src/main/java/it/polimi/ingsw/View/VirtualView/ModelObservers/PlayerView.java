package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.MessageToClient;
import it.polimi.ingsw.View.VirtualView.Messages.PlayerMessageToClient;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

public class PlayerView implements VirtualViewObserver, MessageToClient, Serializable {

    private final Player PLAYER;
    private final VirtualView VIRTUAL_VIEW;

    public PlayerView(Player player, VirtualView virtualView){
        this.PLAYER = player;
        this.VIRTUAL_VIEW = virtualView;
        PLAYER.registerObserver(this);
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new PlayerMessageToClient(PLAYER));
    }
}
