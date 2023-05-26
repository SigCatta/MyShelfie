package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.ChatRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * The ChatObserver class is responsible for observing changes in the ChatRepresentation class and updating the chat in the GUI.
 */
public class ChatObserver implements VirtualModelObserver {

    /**
     * Constructs a new ChatObserver and registers it as an observer for changes in the ChatRepresentation.
     */
    public ChatObserver() {
        ChatRepresentation.getInstance().registerObserver(this);
    }

    /**
     * This method is called when there is an update in the observed VirtualModel.
     * It notifies the StageController to update the chat in the GUI.
     */
    @Override
    public void update() {
        StageController.getController().updateChat();
    }
}