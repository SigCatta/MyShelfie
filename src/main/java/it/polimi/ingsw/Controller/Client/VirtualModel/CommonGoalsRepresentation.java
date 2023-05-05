package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.CommonGoalMessage;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalContainer;

public class CommonGoalsRepresentation implements VirtualModelSubject {
    private CommonGoalContainer commonGoalContainer;

    private static CommonGoalsRepresentation instance;

    private CommonGoalsRepresentation() {
    }


    public static CommonGoalsRepresentation getInstance() {
        if (instance == null) instance = new CommonGoalsRepresentation();
        return instance;
    }

    public void uppdateCommonGoals(CommonGoalMessage commonGoalMessage) {
        System.out.println("-------------------------------------");
        commonGoalContainer = commonGoalMessage.getCommonGoalContainer();
        notifyObservers();
    }

    public CommonGoalContainer getCommonGoalContainer() {
        return commonGoalContainer;
    }

    @Override
    public void registerObserver(VirtualModelObserver observer) {

    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {

    }

    @Override
    public void notifyObservers() {
        synchronized (this){
            notifyAll();
        }
    }

}
