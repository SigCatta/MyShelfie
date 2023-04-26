package it.polimi.ingsw.Controller.Server.VirtualView.ModelObservers;

import it.polimi.ingsw.Controller.Server.VirtualView.Messages.GameMessage;
import it.polimi.ingsw.Controller.Server.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;


public class GameView implements VirtualViewObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public GameView(Game game, VirtualView virtualView){
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new GameMessage(GAME));
    }
}
