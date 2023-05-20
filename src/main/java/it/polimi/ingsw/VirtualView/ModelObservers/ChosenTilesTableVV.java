package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.ChosenTilesTableMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;

public class ChosenTilesTableVV implements VirtualViewObserver {
    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public ChosenTilesTableVV(Game game, VirtualView virtualView) {
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
        game.getChosenTilesTable().registerObserver(this);
    }

    /**
     * when the board changes the updated board is sent to the clients
     */
    @Override
    public void update() {
        VIRTUAL_VIEW.send(new ChosenTilesTableMTC(GAME.getChosenTilesTable().getChosenTiles()));
    }
}
