package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalContainer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonGoalMessage implements MessageToClient, Serializable {

    private Map<String, Integer> cardNamesToPoints;

    public CommonGoalMessage(CommonGoalContainer commonGoalContainer) {
        cardNamesToPoints = new HashMap<>();

        List<CommonGoalCard> commonGoalCards = commonGoalContainer.getCommonGoals();
        List<Set<String>> completedCommonGoal = commonGoalContainer.getCompletedCommonGoal();

        for (int i = 0; i < commonGoalCards.size(); i++) {
            cardNamesToPoints.put(commonGoalCards.get(i).getClass().getName(), commonGoalCards.get(i).peekPoints());
        }

    }

    public Map<String, Integer> getCardNamesToPoints() {
        return cardNamesToPoints;
    }

    @Override
    public void update() {
        CommonGoalsRepresentation.getInstance().changeCommonGoal(this);
    }
}
