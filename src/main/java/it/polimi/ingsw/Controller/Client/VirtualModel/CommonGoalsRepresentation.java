package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.CommonGoalMessageToClient;

import java.util.ArrayList;

public class CommonGoalsRepresentation implements VirtualModelSubject {
    private static CommonGoalsRepresentation instance;
    private CommonGoalMessageToClient commonGoalMessage;

    private CommonGoalsRepresentation() {

    }

    public static CommonGoalsRepresentation getInstance() {
        if (instance == null) instance = new CommonGoalsRepresentation();
        return instance;
    }

    public void updateCommonGoal(CommonGoalMessageToClient commonGoalMessage) {
        this.commonGoalMessage = commonGoalMessage;
    }

    public ArrayList<String> getCardNames() {
        return commonGoalMessage.getCardNames();
    }

    public CommonGoalMessageToClient getCommonGoalMessage() {
        return commonGoalMessage;
    }

    public ArrayList<Integer> getAvailablePoints() {
        return commonGoalMessage.getAvailablePoints();
    }

    @Override
    public void registerObserver(VirtualModelObserver observer) {

    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {

    }

    @Override
    public void notifyObservers() {
        synchronized (this) {
            notifyAll();
        }
    }
}
