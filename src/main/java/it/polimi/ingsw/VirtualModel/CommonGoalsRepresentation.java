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
//        if (this.commonGoalMessage == null) {
        this.commonGoalMessage = commonGoalMessage;
        notifyObservers();
//        }
//        else { // the VM only updates if there actually are changes
//            ArrayList<Integer> newMessagePoints = commonGoalMessage.getAvailablePoints();
//            ArrayList<Integer> oldMessagePoints = this.commonGoalMessage.getAvailablePoints();
//            for (int i = 0; i < oldMessagePoints.size(); i++) {
//                if (!oldMessagePoints.get(i).equals(newMessagePoints.get(i))) {
//                    this.commonGoalMessage = commonGoalMessage;
//
//                    notifyObservers();
//                }
//            }
//        }
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
}
