package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.GameMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;

/**
 * This class is a model observer that listens for changes in the game state and sends an updated game state to the clients.
 */
public class GameVV implements ModelObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    /**
     * Constructs a GameVV object with the specified game and virtual view.
     * It registers itself as an observer of the game.
     *
     * @param game        the game object representing the game
     * @param virtualView the virtual view object used to send messages to the clients
     */
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
