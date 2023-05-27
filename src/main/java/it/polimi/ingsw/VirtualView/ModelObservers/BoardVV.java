package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.BoardMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;

/**
 * This class is a model observer that listens for changes in the game board and sends an updated board to the clients.
 */

public class BoardVV implements ModelObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    /**
     * Constructs a BoardVV object with the specified game and virtual view.
     * It registers itself as an observer of the game board.
     *
     * @param game        the game object representing the game
     * @param virtualView the virtual view object used to send messages to the clients
     */
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
