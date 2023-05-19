package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import javafx.application.Platform;

public class ErrorObserver implements VirtualModelObserver {
    public ErrorObserver() {
        EchosRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        EchoMTC message = EchosRepresentation.getInstance().peekMessage();
        if (message != null && message.isError()) {
            if(BoardController.getInstance()!=null  )
                BoardController.getInstance().updateError();
        }
    }
}
