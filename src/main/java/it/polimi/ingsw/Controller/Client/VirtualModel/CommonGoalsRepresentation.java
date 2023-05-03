package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.CommonGoalMessage;

import java.util.HashMap;
import java.util.Map;

public class CommonGoalsRepresentation implements VirtualModelSubject {
    /**
     * name of the common goal with the points that will be assigned
     * to the next player that achieves it
     */
    private Map<String, Integer> nameToPoints;

    private static CommonGoalsRepresentation instance;

    private CommonGoalsRepresentation() {
        nameToPoints = new HashMap<>();
    }


    public void changeCommonGoal(CommonGoalMessage commonGoalMessage) {
        nameToPoints = commonGoalMessage.getCardNamesToPoints();
    }

    public static CommonGoalsRepresentation getInstance() {
        if (instance == null) instance = new CommonGoalsRepresentation();
        return instance;
    }

    public Map<String, Integer> getNameToPoints() {
        return nameToPoints;
    }

    @Override
    public void registerObserver(VirtualModelObserver observer) {

    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }

}
