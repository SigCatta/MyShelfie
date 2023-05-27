package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.ChosenTilesTableMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;

/**
 * This class is a model observer that listens for changes in the chosen tiles table
 * and sends an updated table to the clients.
 */
public class ChosenTilesTableVV implements ModelObserver {
    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    /**
     * Constructs a ChosenTilesTableVV object with the specified game and virtual view.
     * It registers itself as an observer of the chosen tiles table.
     *
     * @param game        the game object representing the game
     * @param virtualView the virtual view object used to send messages to the clients
     */
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
