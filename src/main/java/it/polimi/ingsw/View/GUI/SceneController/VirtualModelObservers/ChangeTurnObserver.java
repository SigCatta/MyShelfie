package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class ChangeTurnObserver implements VirtualModelObserver {

    private String previousPlayer;

    public ChangeTurnObserver() {
        previousPlayer = GameRepresentation.getInstance().getActivePlayerNickname();
        GameRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        if (previousPlayer.equals(GameRepresentation.getInstance().getActivePlayerNickname())) return;

        previousPlayer = GameRepresentation.getInstance().getActivePlayerNickname();
        BoardController.getInstance().updateChangeTurn();
    }
}
