package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.CommonGoalMessage;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalContainer;

public class CommonGoalContainerView implements VirtualViewObserver {

    private final CommonGoalContainer commonGoalContainer;
    private final VirtualView VIRTUAL_VIEW;

    public CommonGoalContainerView(CommonGoalContainer commonGoalContainer, VirtualView virtualView) {
        this.commonGoalContainer = commonGoalContainer;
        this.VIRTUAL_VIEW = virtualView;
        commonGoalContainer.registerObserver(this);
    }

    @Override
    public void update() {
        VIRTUAL_VIEW.send(new CommonGoalMessage(commonGoalContainer));
    }
}
