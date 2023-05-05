package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.BoardMessageToClient;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;

public class BoardView implements VirtualViewObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public BoardView(Game game, VirtualView virtualView){
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
        GAME.getBoard().registerObserver(this);
    }

    /**
     * when the board changes the updated board is sent to the clients
     */
    @Override
    public void update() {
        BoardMessageToClient message = new BoardMessageToClient(GAME.getBoard());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        VIRTUAL_VIEW.send(message);
    }
}
