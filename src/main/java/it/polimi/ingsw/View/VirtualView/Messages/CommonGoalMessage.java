package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.model.cards.commonGoals.CommonGoalContainer;

import java.io.Serializable;

public class CommonGoalMessage implements MessageToClient, Serializable {

    private final CommonGoalContainer commonGoalContainer;

    public CommonGoalMessage(CommonGoalContainer commonGoalContainer) {
        this.commonGoalContainer = commonGoalContainer;
    }

    public CommonGoalContainer getCommonGoalContainer() {
        return commonGoalContainer;
    }

    @Override
    public void update() {
        System.out.println("§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§");
//        CommonGoalsRepresentation.getInstance().uppdateCommonGoals(this);
    }
}
