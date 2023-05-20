package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.BoardMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;

public class BoardVV implements VirtualViewObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public BoardVV(Game game, VirtualView virtualView) {
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
        GAME.getBoard().registerObserver(this);
    }

    /**
     * when the board changes the updated board is sent to the clients
     */
    @Override
    public void update() {
        VIRTUAL_VIEW.send(new BoardMTC(GAME.getBoard()));
    }
}
