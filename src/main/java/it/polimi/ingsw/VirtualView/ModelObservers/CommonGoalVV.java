package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.CommonGoalMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.EndOfTurn.EndOfTurnObserver;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;

/**
 * The CommonGoalVV class is a model observer that listens for changes in the common goals
 * and sends an updated common goals list to the clients.
 */
public class CommonGoalVV implements ModelObserver, EndOfTurnObserver {
    private final Game game;
    private final VirtualView VIRTUAL_VIEW;

    /**
     * Constructs a CommonGoalVV object with the specified game and virtual view.
     * It registers itself as an observer of all the common goal cards in the game.
     * It also sends the initial common goals list to the clients.
     *
     * @param game        the game object representing the game
     * @param virtualView the virtual view object used to send messages to the clients
     */
    public CommonGoalVV(Game game, VirtualView virtualView) {
        this.game = game;
        this.VIRTUAL_VIEW = virtualView;
        for (CommonGoalCard cg : game.getCommonGoals()) {
            cg.registerObserver(this);
        }
        update();
    }

    @Override
    public void update() {
        if (VIRTUAL_VIEW == null) return; // testing...
        VIRTUAL_VIEW.send(new CommonGoalMTC(game.getCommonGoals()));
    }
}
