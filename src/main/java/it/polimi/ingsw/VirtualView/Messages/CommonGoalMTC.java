package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a message to the client containing the information about the common goal cards.
 */
public class CommonGoalMTC implements MessageToClient, Serializable {
    private final ArrayList<String> cardNames;
    private final ArrayList<Integer> availablePoints;

    public CommonGoalMTC(ArrayList<CommonGoalCard> commonGoalCards) {
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
