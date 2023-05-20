package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.GameMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;


public class GameVV implements VirtualViewObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public GameVV(Game game, VirtualView virtualView) {
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
        GAME.registerObserver(this);
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new GameMTC(GAME));
    }
}
