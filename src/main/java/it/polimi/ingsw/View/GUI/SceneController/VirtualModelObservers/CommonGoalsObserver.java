package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class CommonGoalsObserver implements VirtualModelObserver {
    public CommonGoalsObserver() {
        CommonGoalsRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        StageController.getController().updateCommonGoals();
    }
}
