package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;

/**
 * The EchoObserver class is responsible for observing changes in the EchosRepresentation class and updating the echo messages in the GUI.
 */
public class EchoObserver implements VirtualModelObserver {

    /**
     * Constructs a new EchoObserver and registers it as an observer for changes in the EchosRepresentation.
     */
    public EchoObserver() {
        EchosRepresentation.getInstance().registerObserver(this);
    }

    /**
     * This method is called when there is an update in the observed VirtualModel.
     * It retrieves the latest echo message from the EchosRepresentation and notifies the StageController to update the echo message in the GUI.
     */
    @Override
    public void update() {
        EchoMTC message = EchosRepresentation.getInstance().peekMessage();
        StageController.getController().updateEcho(message);
    }
}