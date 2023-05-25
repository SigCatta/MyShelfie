package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.CommonGoalMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.EndOfTurn.EndOfTurnObserver;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;

public class CommonGoalVV implements ModelObserver, EndOfTurnObserver {
    private final Game game;
    private final VirtualView VIRTUAL_VIEW;

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
