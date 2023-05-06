package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;

import java.io.Serializable;
import java.util.ArrayList;

public class CommonGoalMessageToClient implements MessageToClient, Serializable {
    private final ArrayList<String> cardNames;
    private final ArrayList<Integer> availablePoints;

    public CommonGoalMessageToClient(ArrayList<CommonGoalCard> commonGoalCards) {
        cardNames = new ArrayList<>();
        availablePoints = new ArrayList<>();
        commonGoalCards.forEach(cg -> {
            cardNames.add(cg.getCardName());
            availablePoints.add(cg.peekPoints());
        });
    }

    public ArrayList<String> getCardNames() {
        return cardNames;
    }

    public ArrayList<Integer> getAvailablePoints() {
        return availablePoints;
    }

    @Override
    public void update() {
        CommonGoalsRepresentation.getInstance().updateCommonGoal(this);
    }
}
