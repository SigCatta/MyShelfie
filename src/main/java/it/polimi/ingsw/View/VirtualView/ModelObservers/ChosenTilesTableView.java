package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.ChosenTilesTableMessage;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;

public class ChosenTilesTableView implements VirtualViewObserver{
    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public ChosenTilesTableView(Game game, VirtualView virtualView){
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
    }

    /**
     * when the board changes the updated board is sent to the clients
     */
    @Override
    public void update() {
        VIRTUAL_VIEW.send(new ChosenTilesTableMessage(GAME.getTilesGetter().getChosenTilesTable().getCHOSEN_TILES()));
    }
}
