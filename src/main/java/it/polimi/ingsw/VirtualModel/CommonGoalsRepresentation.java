package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.CommonGoalMTC;

import java.util.ArrayList;

public class CommonGoalsRepresentation extends VirtualModelSubject {
    private static CommonGoalsRepresentation instance;
    private CommonGoalMTC commonGoalMessage;

    private CommonGoalsRepresentation() {
        super();
    }

    public static CommonGoalsRepresentation getInstance() {
        if (instance == null) instance = new CommonGoalsRepresentation();
        return instance;
    }

    public void updateCommonGoal(CommonGoalMTC commonGoalMessage) {
        this.commonGoalMessage = commonGoalMessage;
        notifyObservers();
    }

    public ArrayList<String> getCardNames() {
        return commonGoalMessage.getCardNames();
    }

    public CommonGoalMTC getCommonGoalMessage() {
        return commonGoalMessage;
    }

    public ArrayList<Integer> getAvailablePoints() {
        return commonGoalMessage.getAvailablePoints();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(VirtualModelObserver::update);
    }
}
