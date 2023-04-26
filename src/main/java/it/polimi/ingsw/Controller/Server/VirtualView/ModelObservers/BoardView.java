package it.polimi.ingsw.Controller.Server.VirtualView.ModelObservers;

import it.polimi.ingsw.Controller.Server.VirtualView.VirtualView;
import it.polimi.ingsw.Controller.Server.VirtualView.Messages.BoardMessage;
import it.polimi.ingsw.model.Game;

public class BoardView implements VirtualViewObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public BoardView(Game game, VirtualView virtualView){
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
    }

    /**
     * when the board changes the updated board is sent to the clients
     */
    @Override
    public void update() {
        VIRTUAL_VIEW.send(new BoardMessage(GAME.getBoard()));
    }
}
