package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.PongExecutor;

import java.io.Serializable;

public class PongMTS extends MessageToServer implements Serializable {
    @Override
    public void update() {
        PongExecutor.execute(this);
    }
}
