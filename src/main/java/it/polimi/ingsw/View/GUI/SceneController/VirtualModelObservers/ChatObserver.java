package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.VirtualModel.ChatRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class ChatObserver implements VirtualModelObserver {

    public ChatObserver() {
        ChatRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        //ChatController.getInstance().updateChat();
    }
}