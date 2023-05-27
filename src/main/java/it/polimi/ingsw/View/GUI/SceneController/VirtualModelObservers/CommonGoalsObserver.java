package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * The CommonGoalsObserver class is responsible for observing changes in the CommonGoalsRepresentation class and updating the common goals in the GUI.
 */
public class CommonGoalsObserver implements VirtualModelObserver {

    /**
     * Constructs a new CommonGoalsObserver and registers it as an observer for changes in the CommonGoalsRepresentation.
     */
    public CommonGoalsObserver() {
        CommonGoalsRepresentation.getInstance().registerObserver(this);
    }

    /**
     * This method is called when there is an update in the observed VirtualModel.
     * It notifies the StageController to update the common goals in the GUI.
     */
    @Override
    public void update() {
        StageController.getController().updateCommonGoals();
    }
}
