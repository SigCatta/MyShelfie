package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;

public class EchoObserver implements VirtualModelObserver {
    public EchoObserver() {
        EchosRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        EchoMTC message = EchosRepresentation.getInstance().peekMessage();
        StageController.getController().updateEcho(message);
    }
}
