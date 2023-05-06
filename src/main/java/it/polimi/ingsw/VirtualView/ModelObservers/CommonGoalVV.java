package it.polimi.ingsw.VirtualView.ModelObservers;

import it.polimi.ingsw.VirtualView.Messages.CommonGoalMTC;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;

import java.util.ArrayList;

public class CommonGoalVV implements VirtualViewObserver {
    private final ArrayList<CommonGoalCard> commonGoalCards;
    private final VirtualView VIRTUAL_VIEW;

    public CommonGoalVV(Game game, VirtualView virtualView) {
        this.commonGoalCards = game.getCommonGoals();
        this.VIRTUAL_VIEW = virtualView;
        this.commonGoalCards.forEach(cg -> cg.registerObserver(this));
        game.registerObserver(this);
        update();
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new CommonGoalMTC(commonGoalCards));
    }
}
