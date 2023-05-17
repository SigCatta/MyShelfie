package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.ChatController;
import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.ChatRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import javafx.application.Platform;

public class ChatObserver implements VirtualModelObserver {

    public ChatObserver() {
        ChatRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        Platform.runLater(() -> ChatController.getInstance().updateChat()
        );
    }
}