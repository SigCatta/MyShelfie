package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.GameMessage;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;


public class GameView implements VirtualViewObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public GameView(Game game, VirtualView virtualView){
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
        GAME.registerObserver(this);
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new GameMessage(GAME));
    }
}
