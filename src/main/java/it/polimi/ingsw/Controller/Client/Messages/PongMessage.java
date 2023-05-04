package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.Executor.PongExecutor;

import java.io.Serializable;

public class PongMessage extends MessageToServer implements Serializable {
    @Override
    public void update() {
        PongExecutor.execute(this);
    }
}
