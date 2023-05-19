package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class ChangeTurnObserver implements VirtualModelObserver {

    private String previousPlayer;

    public ChangeTurnObserver() {
        GameRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {

        if(previousPlayer != null && !previousPlayer.equals(GameRepresentation.getInstance().getActivePlayerNickname())) return;

        // done if it is the first turn or the turn is changed
        previousPlayer = GameRepresentation.getInstance().getActivePlayerNickname();
        StageController.getController().updateChangeTurn();

    }
}
